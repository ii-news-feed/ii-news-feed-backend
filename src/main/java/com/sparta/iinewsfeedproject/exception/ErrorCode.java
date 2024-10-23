package com.sparta.iinewsfeedproject.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    DUPLICATION_EMAIL(HttpStatus.UNAUTHORIZED,"중복된 이메일입니다."),
    INCORRECT_EMAIL_FORMAT(HttpStatus.UNAUTHORIZED,"잘못된 이메일 형식입니다."),
    INCORRECT_PASSWORD_FORMAT(HttpStatus.UNAUTHORIZED,"비밀번호는 영문, 숫자, 기호를 포함한 8자 이상으로 작성해 주십시오."),
    NOT_MATCH_PASSWORD(HttpStatus.UNAUTHORIZED,"비밀번호가 일치하지 않습니다."),
    NOT_REGISTRATION_USER(HttpStatus.UNAUTHORIZED,"등록되지 않은 회원입니다."),
    NULL_CONTENT(HttpStatus.UNAUTHORIZED,"내용을 입력해주세요."),
    NULL_MODIFY_CONTENT(HttpStatus.UNAUTHORIZED,"수정하실 내용을 입력해주세요."),
    NOT_MATCH_LOGIN(HttpStatus.BAD_REQUEST,"이메일 또는 비밀번호가 일치하지 않습니다."),
    NOT_TOKEN(HttpStatus.BAD_REQUEST,"재 로그인이 필요합니다."),
    NOT_WRITE_USER(HttpStatus.BAD_REQUEST,"본인의 게시물만 삭제 및 수정할 수 있습니다"),
    NOT_FOUND_POST(HttpStatus.NOT_FOUND,"존재하지 않는 게시물입니다."),
    NOT_FOUND_USER(HttpStatus.NOT_FOUND,"존재하지 않는 유저번호 입니다."),
    NOT_FOUND_FROM_USER(HttpStatus.NOT_FOUND,"요청할 사람의 ID가 존재하지 않습니다.");

    private final int status;
    private final String message;

    ErrorCode(final HttpStatus status, final String message) {
        this.status = status.value();
        this.message = message;
    }
}
