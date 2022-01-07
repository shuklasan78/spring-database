package com.springoot.dom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.time.LocalDateTime;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity( name="PostsEntity")
public class PostsEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private LocalDateTime analyseDate ;
    private String failedSummary ;
    private String analyseTimeInSeconds ;
    private Date firstPostDate ;
    private Date lastPostDate ;
    private String totalPosts;
    private String totalAcceptedPosts;
    private String avgScore;
    private String state;
    private String posts;
}
