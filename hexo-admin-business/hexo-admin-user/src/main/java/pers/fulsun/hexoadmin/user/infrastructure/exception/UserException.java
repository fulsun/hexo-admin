package pers.fulsun.hexoadmin.user.infrastructure.exception;

import pers.fulsun.hexoadmin.base.exception.BizException;
import pers.fulsun.hexoadmin.base.exception.ErrorCode;

public class UserException extends BizException {
    public UserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
