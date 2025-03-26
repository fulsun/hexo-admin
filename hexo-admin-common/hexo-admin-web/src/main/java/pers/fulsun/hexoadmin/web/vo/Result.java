package pers.fulsun.hexoadmin.web.vo;

import lombok.Getter;
import lombok.Setter;

/**
 *
 */
@Getter
@Setter
public class Result<T> {
    /**
     * 状态吗
     */
    private String code;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 消息描述
     */
    private String message;

    /**
     * 数据，可以是任何类型的VO
     */
    private T data;

    public static <T> Result<T> success(T data) {
        Result result = new Result<>();
        result.setSuccess(true);
        result.setCode("200");
        result.setMessage("success");
        result.setData(data);
        return result;
    }
}
