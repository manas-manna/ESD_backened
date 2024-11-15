package com.manasmann.esdprogram.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data
public class UnauthorisedAccessException extends RuntimeException {
    private final String msg;
    public UnauthorisedAccessException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
