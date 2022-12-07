package com.sparta.crud.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignupRequestDto {

    // 최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)
    @NotNull(message = "아이디는 필수 값입니다.")
    @Size(min = 4, max = 10, message = "최소 4자 이상, 10자 이하를 입력해주세요.")
    @Pattern(regexp = "^[a-z0-9]*$", message = "알파벳 소문자(a~z), 숫자(0~9)만 사용 가능합니다.")
    private String username;

    // 최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)
    @NotNull(message = "비밀번호는 필수 값입니다.")
    @Size(min = 8, max = 15, message = "최소 8자 이상, 15자 이하를 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9`~!@#$%^&*()-_=+]*$", message = "알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자만 사용 가능합니다.")
    private String password;

    private boolean admin = false;

    private String adminToken = "";
}
