package com.springoot.dom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AnalysisException extends RuntimeException {

    public AnalysisException(String exception) {
        super(exception);
    }
}
