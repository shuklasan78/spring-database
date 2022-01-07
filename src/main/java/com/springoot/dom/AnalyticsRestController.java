package com.springoot.dom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
public class AnalyticsRestController {
    @Autowired
    private PostsEntityRepository repository;
    @Autowired
    private ParseXML parserXML;
    @GetMapping("poc")
    public ResponseEntity<Analysis> retrieveAnalysisSummary(@RequestParam String displayAll ) throws Exception {

        if("true".equals(displayAll)) {
            return  new ResponseEntity<>(parserXML.getAnalysis(true), HttpStatus.ACCEPTED);
        }
        else if("false".equals(displayAll)) {

            return  new ResponseEntity<>(parserXML.getAnalysis(false), HttpStatus.ACCEPTED);
        }
        else {
            throw new AnalysisException("`The value entered in the request parameter is not correct");
        }
    }
    @DeleteMapping("poc/{id}")
    public ResponseEntity<String> deleteAnalysis(@PathVariable String id ) throws Exception {

        if("all".equals(id)) {
            repository.deleteAll();
            return  new ResponseEntity<>("Successfully deleted All records", HttpStatus.ACCEPTED);
        }
        else {
            Optional<PostsEntity> entity = repository.findById(Long.valueOf(id));
            if(entity.isPresent()){
                repository.deleteById(Long.valueOf(id));
                return  new ResponseEntity<>("Successfully records deleted", HttpStatus.ACCEPTED);
            }
            else{
                throw new RecordNotFoundException("ID does not exits in database");
            }
        }

}

    @DeleteMapping("poc")
    public ResponseEntity<String> deleteAnalysisAll() throws Exception {
            repository.deleteAll();
            return  new ResponseEntity<>("Successfully deleted All records", HttpStatus.ACCEPTED);

        }



}

