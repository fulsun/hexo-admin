package pers.fulsun.hexoadmin.exception.impl;

import pers.fulsun.hexoadmin.exception.ErrorCode;

public enum SystemErrorCode implements ErrorCode {
    BAD_REQUEST("400", "请求错误"),
    UNAUTHORIZED("401", "未授权"),
    FORBIDDEN("403", "禁止访问"),
    NOT_FOUND("404", "找不到资源"),
    TOO_MANY_REQUESTS("429", "请求过于频繁"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误"),
    NOT_IMPLEMENTED("501", "功能未实现"),
    SERVICE_UNAVAILABLE("503", "服务不可用"),
    GATEWAY_TIMEOUT("504", "网关超时"),
    UNKNOWN_ERROR("999", "未知错误");

    private String code;

    private String message;

    SystemErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
