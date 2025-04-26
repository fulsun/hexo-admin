package pers.fulsun.hexoadmin.auth.exception;

import pers.fulsun.hexoadmin.base.exception.ErrorCode;

public enum AuthErrorCode implements ErrorCode {
    /**
     * 用户未登录
     */
    USER_NOT_LOGIN("USER_NOT_LOGIN", "用户未登录"),
    
    USER_LOGIN_FAILED("USER_LOGIN_FAILED", "登录校验失败");
    private String code;
    
    private String message;
    
    AuthErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
    
    @Override
    public String getCode() {
        return code;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
}
