package com.pachimari.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by andrem on 29/03/2017.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidException extends RuntimeException{
}