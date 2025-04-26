package pers.fulsun.hexoadmin.user.infrastructure.exception;

import pers.fulsun.hexoadmin.base.exception.ErrorCode;

public enum UserErrorCode implements ErrorCode {
    USER_EXIST("USER_EXIST", "用户已存在"),
    USER_DISABLED("USER_DISABLE", "账号被禁用"),
    USER_NOT_EXIST("USER_NOT_EXIST", "用户不存在"),
    USER_DEREGISTRATION("USER_CANCELLED", "账号被注销"),
    DUPLICATE_TELEPHONE_NUMBER("DUPLICATE_TELEPHONE_NUMBER", "重复电话号码"),
    USERNAME_OR_PASSWORD_ERROR("USERNAME_OR_PASSWORD_ERROR", "用户名或密码错误"),
    USER_OPERATE_FAILED("USER_OPERATE_FAILED", "用户操作失败");
    
    private String code;
    
    private String message;
    
    UserErrorCode(String code, String message) {
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
