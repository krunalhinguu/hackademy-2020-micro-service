package com.krunal.api.email.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class MailResponse {

    private String message;
    private boolean status;

    public MailResponse() {
    }

    public MailResponse(String message, boolean status) {
        this.message = message;
        this.status = status;
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
        return "MailResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
