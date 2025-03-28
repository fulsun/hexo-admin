package pers.fulsun.hexoadmin.base.exception;

/**
 * 错误码
 *
 * @author fulsun
 */
public interface ErrorCode {
    
    /**
     * 错误码
     *
     * @return 错误码
     */
    String getCode();
    
    /**
     * 错误信息
     *
     * @return 错误信息
     */
    String getMessage();
}
