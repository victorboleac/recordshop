package com.northcoders.recordshop.cash;


public class Cash<T> {


    private final T object;
    private final Long timestamp;

    public Cash(T object, Long timestamp) {
        this.object = object;
        this.timestamp = timestamp;
    }
    public T getObject() {
        return object;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}
