package com.FuelBee.backend.response;

public class DealerResponse {
    private String message;
    private String id;
    private int statusCode;

    public DealerResponse(String message, String id, int statusCode) {
        this.message = message;
        this.id = id;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
