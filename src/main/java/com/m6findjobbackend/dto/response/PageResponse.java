package com.m6findjobbackend.dto.response;

public class PageResponse {
    private Long totalRecord;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Long totalRecord) {
        this.totalRecord = totalRecord;
    }
}
