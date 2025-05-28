package com.FuelBee.backend.response;

public class DealerResponse {
    private String message;
    private String id;
    private String statusCode;

    public DealerResponse(String message, String id, String statusCode) {
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

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
