package com.sobernt.tasklist.model;
public class Response<T> {
    public int code = 0;
    public String message = "";
    public T result;
}
