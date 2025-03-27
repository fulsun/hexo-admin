package pers.fulsun.hexoadmin.api.user.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pers.fulsun.hexoadmin.base.response.BaseResponse;

@Setter
@Getter
@ToString
public class UserQueryResponse<T> extends BaseResponse {
    
    private static final long serialVersionUID = 1L;
    
    private T data;
}
