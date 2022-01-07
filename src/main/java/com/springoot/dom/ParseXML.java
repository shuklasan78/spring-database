package com.springoot.dom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class ParseXML {
    @Autowired
    private  PostsEntityRepository repository;
    @Autowired
    private static ResourceLoader resourceLoader;

    private String loadXMLAsString() throws IOException {
        Resource rs = new ClassPathResource("domfiles/arabic-posts.xml");
        return new String(Files.readAllBytes(rs.getFile().toPath()));
    }
    public List<RowId> loadXMLData() throws IOException {
        Resource rs = new ClassPathResource("domfiles/arabic-posts.xml");
        String text = new String(Files.readAllBytes(rs.getFile().toPath()));
        JAXBContext jaxbContext;
        List<RowId> lst = null;
        try {
            jaxbContext = JAXBContext.newInstance(Posts.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(text);
            Posts rows = (Posts) unmarshaller.unmarshal(reader);
            lst = rows.getRow();

        }

        catch (JAXBException e) {
            log.error("Error Occurred while loaing XML in object", e.getMessage());
        }
        return lst;
    }

    public Analysis getAnalysis( boolean responseParam) throws IOException {
        List<RowId> rows = this.loadXMLData();
        int i =0;
        int score = 0;
        int totalAcceptedPost =0;
        LocalDateTime localDateTime= LocalDateTime.now();
        Analysis analysisLocal = new Analysis();
        analysisLocal.setAnalyseDate(localDateTime);
        analysisLocal.setState(State.ANALYSING);
        Details details = new Details();
        details.setTotalPosts(String.valueOf(rows.size()));
        for(RowId row : rows) {
            if(i==0) {
                details.setFirstPost(row.getCreationDate());
            }
            if(i==rows.size()-1) {
                details.setLastPost(row.getCreationDate());
            }

            if(null!=row.getAcceptedAnswerId()) {
                totalAcceptedPost++;
            }
            score = score + Integer.valueOf(row.getScore());

            i++;
        }
        log.debug(String.valueOf(score));
        analysisLocal.setAnalyseTimeInSeconds(String.valueOf(localDateTime.getSecond()-LocalDateTime.now().getSecond()));
        analysisLocal.setDetails(details);
        if(responseParam) {
            analysisLocal.setPosts(rows);
        }
        details.setTotalAcceptedPosts(String.valueOf(totalAcceptedPost));
        details.setAvgScore(String.valueOf(score/Double.valueOf(rows.size())));
        log.debug("Analysis analysisLocal='{}'",analysisLocal);
        String analySisiId = String.valueOf(savePosts(analysisLocal).getId());
        analysisLocal.setId(analySisiId);

        return analysisLocal;
    }

    public PostsEntity savePosts(Analysis obj) throws IOException {

        PostsEntity entity = new PostsEntity();
        entity.setAvgScore(obj.getAnalyseTimeInSeconds());
        entity.setAnalyseTimeInSeconds(obj.getAnalyseTimeInSeconds());
        entity.setState(obj.getState().name());
        entity.setTotalPosts(obj.getDetails().getTotalPosts());
        entity.setTotalAcceptedPosts(obj.getDetails().getTotalAcceptedPosts());
        //entity.setPosts(loadXMLAsString());
        repository.save(entity);
        entity.setAnalyseDate(LocalDateTime.now());
        return repository.save(entity);
    }

}