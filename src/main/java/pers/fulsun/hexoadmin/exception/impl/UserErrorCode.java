package pers.fulsun.hexoadmin.exception.impl;

import pers.fulsun.hexoadmin.exception.ErrorCode;

public enum UserErrorCode implements ErrorCode {
    DUPLICATE_TELEPHONE_NUMBER("DUPLICATE_TELEPHONE_NUMBER", "重复电话号码"),

    USER_NOT_EXIST("USER_NOT_EXIST", "用户不存在"),
    NICK_NAME_EXIST("NICK_NAME_EXIST", "用户名已存在"),
    USER_PASSWD_CHECK_FAIL("USER_PASSWD_CHECK_FAIL", "用户密码校验失败"),
    USER_NOT_LOGIN("401", "用户未登录"),
    USER_QUERY_FAIL("USER_QUERY_FAIL", "用户查询失败"),

    USER_CREATE_CHAIN_FAIL("USER_CREATE_CHAIN_FAIL", "用户创建链账号失败");

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
