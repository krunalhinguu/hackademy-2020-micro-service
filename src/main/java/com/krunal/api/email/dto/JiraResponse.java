package com.krunal.api.email.dto;

public class JiraResponse {

    private String id;
    private String message;
    private boolean status;

    public JiraResponse() {
    }

    public JiraResponse(String id, String message, boolean status) {
        this.id = id;
        this.message = message;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "JiraResponse{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
