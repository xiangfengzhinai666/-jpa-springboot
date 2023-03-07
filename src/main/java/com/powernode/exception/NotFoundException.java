package com.powernode.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author 香风智乃
 * @className NotFoundException
 * @date 2023/2/24 10:54
 * @desciption:
 */

@ResponseStatus(HttpStatus.NOT_FOUND)  //如果抛出这个异常，因为注解里面写的是notfound 所以程序会直接找404
public class NotFoundException extends RuntimeException{

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
