package com.usher.springboot.blog.vo;

/**
 * @Author: Usher
 * @Description:
 * 返回值前端格式
 */
public class ResponseVO {
    private boolean success;
    private String message;
    private Object body;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public Object getBody() {
        return body;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public ResponseVO(boolean success, String message, Object body) {
        this.success = success;
        this.message = message;
        this.body = body;
    }

    public ResponseVO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
