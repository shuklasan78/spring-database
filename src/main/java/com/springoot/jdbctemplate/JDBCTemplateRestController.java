package com.springoot.jdbctemplate;

import com.springoot.mysql.sales.SalesRecordsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/jdbctemplate")
public class JDBCTemplateRestController {

    @Autowired
     JDBCTemplateServiceRepository jdbcRepositoryService;

    @GetMapping("/sales")
    public ResponseEntity<List<SalesRecordsEntity>> getAllTutorials(@RequestParam(required = false) String title) {
        try {
            List<SalesRecordsEntity> tutorials = new ArrayList<SalesRecordsEntity>();

            if (title == null) {
                jdbcRepositoryService.findAll().forEach(tutorials::add);
            } else
                jdbcRepositoryService.findByTitleContaining(title).forEach(tutorials::add);

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sales/{id}")
    public ResponseEntity<SalesRecordsEntity> getTutorialById(@PathVariable("id") Integer id) {
        SalesRecordsEntity tutorial = jdbcRepositoryService.findById(id);

        if (tutorial != null) {
            return new ResponseEntity<>(tutorial, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/sales")
    public ResponseEntity<String> createTutorial(@RequestBody SalesRecordsEntity tutorial) {
        try {
            SalesRecordsEntity saleRec = new SalesRecordsEntity();
            saleRec.setCountry("India");
            jdbcRepositoryService.save(saleRec);
            return new ResponseEntity<>("Sales Records was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/sales/{id}")
    public ResponseEntity<String> updateTutorial(@PathVariable("id") Integer id, @RequestBody SalesRecordsEntity SalesInputObj) {
        SalesRecordsEntity saleObj = jdbcRepositoryService.findById(id);

        if (saleObj != null) {
            saleObj.setId(id);
            saleObj.setCountry(SalesInputObj.getCountry());
            saleObj.setItemtype(SalesInputObj.getItemtype());
            jdbcRepositoryService.update(saleObj);
            return new ResponseEntity<>("Tutorial was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find Tutorial with id=" + id, HttpStatus.NOT_FOUND);
        }
    }
}
