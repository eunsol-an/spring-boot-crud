package com.sparta.crud.dto;

public enum StatusEnum {

    OK(200, "성공"),
    PLUS_LIKE(200, "좋아요 완료"),
    MINUS_LIKE(200, "좋아요 취소");

    int statusCode;
    String msg;

    StatusEnum(int statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }

}
