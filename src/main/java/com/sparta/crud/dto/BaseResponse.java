package com.sparta.crud.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    private int status;
    private String msg;


    public BaseResponse(StatusEnum status) {
        this.status = status.statusCode;
        this.msg = status.msg;
    }
}
