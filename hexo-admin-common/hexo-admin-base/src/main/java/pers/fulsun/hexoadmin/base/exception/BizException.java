package pers.fulsun.hexoadmin.base.exception;

public class BizException extends RuntimeException{
    private ErrorCode errorCode;

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
