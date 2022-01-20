package com.m6findjobbackend.dto.request;

public class StatusRequest {
    private int status;

    public StatusRequest() {
    }

    public StatusRequest(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
