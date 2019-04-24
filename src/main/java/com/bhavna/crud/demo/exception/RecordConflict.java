package com.bhavna.crud.demo.exception;

public class RecordConflict extends RuntimeException {
    public RecordConflict(String exception) {
        super(exception);
    }
}
