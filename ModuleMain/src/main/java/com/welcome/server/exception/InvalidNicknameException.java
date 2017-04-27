package com.welcome.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by @mistreckless on 07.11.2016.!
 */
@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InvalidNicknameException extends IllegalArgumentException {
    public InvalidNicknameException(String message){
        super(message);
    }
}
