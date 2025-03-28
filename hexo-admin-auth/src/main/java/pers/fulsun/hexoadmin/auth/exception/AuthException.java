package pers.fulsun.hexoadmin.auth.exception;

import pers.fulsun.hexoadmin.base.exception.BizException;
import pers.fulsun.hexoadmin.base.exception.ErrorCode;

public class AuthException extends BizException {
    
    public AuthException(ErrorCode errorCode) {
        super(errorCode);
    }
}
