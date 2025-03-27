package pers.fulsun.hexoadmin.user.infrastructure.exception;

import pers.fulsun.hexoadmin.base.exception.ErrorCode;

public enum UserErrorCode implements ErrorCode {
    /**
     * 重复电话号码
     */
    DUPLICATE_TELEPHONE_NUMBER("DUPLICATE_TELEPHONE_NUMBER", "重复电话号码"),
    /**
     * 用户操作失败
     */
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
