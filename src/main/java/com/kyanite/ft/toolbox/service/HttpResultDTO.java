package com.kyanite.ft.toolbox.service;

import org.springframework.http.HttpStatus;

/**
 * Created by Alex on 2017/4/10.
 */
public class HttpResultDTO {
    private Integer httpStatus;
    private String data;

    public HttpResultDTO(Integer httpStatus, String data) {
        this.httpStatus = httpStatus;
        this.data = data;
    }

    public HttpResultDTO() {
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    public boolean isOk() {
        return this.getHttpStatus().equals(HttpStatus.OK.value());
    }

    @Override
    public String toString() {
        return "HttpResultDTO{" +
            "httpStatus=" + httpStatus +
            ", data='" + data + '\'' +
            '}';
    }
}
