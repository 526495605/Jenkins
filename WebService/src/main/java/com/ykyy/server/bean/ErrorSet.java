package com.ykyy.server.bean;

import java.io.Serializable;

public class ErrorSet implements Serializable
{

    private String message;
    private int statusCode;
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {

        return message;
    }

    public ErrorSet() {
    }

    public ErrorSet(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
