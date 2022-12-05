package com.sparta.crud.dto;

import lombok.Getter;

@Getter
public class BaseResponse {
    public Boolean success;
    public int statusCode;

    public BaseResponse(Boolean success, int statusCode) {
        this.success = success;
        this.statusCode = statusCode;
    }
}
