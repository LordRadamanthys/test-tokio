package com.example.api.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable {

    public static final Long serialVersionUID = 1l;
    private Long timestamp;
    private Integer status;
    private String error;
    private String message;

    public StandardError(Long timestamp, Integer status, String message, String error){
        this.error = error;
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
