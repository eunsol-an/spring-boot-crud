package com.sparta.crud.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    private int statusCode;
    private String msg;


    public BaseResponse(StatusEnum status) {
        this.statusCode = status.statusCode;
        this.msg = status.msg;
    }
}
