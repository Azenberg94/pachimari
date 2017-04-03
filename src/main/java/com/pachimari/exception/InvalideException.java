package com.pachimari.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by goasguenl on 17/03/2017.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalideException extends RuntimeException {
}

