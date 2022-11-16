package org.team3.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorType {
    INTERNAL_ERROR(2000, "Internal Server Error", INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(2001, "Invalid Parameter Error", BAD_REQUEST),
    LOGIN_ERROR_WRONG(1000,"Kullanıcı adı yada şifre hatalı",INTERNAL_SERVER_ERROR),
    INVALID_TOKEN(2002, "Invalid Token", BAD_REQUEST),
    LOGIN_ERROR_REQUIRED_PASSWORD(1001,"Şifre zorunlulukları, geçerli bir şifre giriniz",INTERNAL_SERVER_ERROR),
    INVALID_USER(1002,"Kullanıcı bulunamadı", INTERNAL_SERVER_ERROR);


    private int code;
    private String message;
    HttpStatus httpStatus;
}
