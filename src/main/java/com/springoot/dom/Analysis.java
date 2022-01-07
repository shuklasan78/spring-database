package com.springoot.dom;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Analysis {

    private String id;

    // current time and date
    private LocalDateTime analyseDate;
    // optional text field
    private String failedSummary;
    private String analyseTimeInSeconds;
    private Details details;
    private State state;
    private List<RowId> posts;
}
