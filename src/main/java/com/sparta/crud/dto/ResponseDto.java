package com.sparta.crud.dto;

import lombok.Getter;

@Getter
public class ResponseDto {
    private String success;
    private int statusCode;

    public ResponseDto(String success, int statusCode) {
        this.success = success;
        this.statusCode = statusCode;
    }
}
