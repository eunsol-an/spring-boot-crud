package com.sparta.crud.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CutomException extends RuntimeException{
    private final ErrorCode errorCode;
}
