package com.sparta.crud.dto;

import lombok.Getter;

@Getter
public class BaseResponse {
    private Boolean success;
    private int statusCode;

    public BaseResponse(Boolean success, int statusCode) {
        this.success = success;
        this.statusCode = statusCode;
    }
}
