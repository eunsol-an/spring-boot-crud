package com.sparta.crud.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class SignupRequestDto {

    // 최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)
    @NotNull(message = "아이디는 필수 값입니다.")
    @Pattern(regexp = "^[a-z0-9]{4,10}")
    private String username;

    // 최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)
    @NotNull(message = "비밀번호는 필수 값입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}")
    private String password;

    private boolean admin = false;

    private String adminToken = "";
}
