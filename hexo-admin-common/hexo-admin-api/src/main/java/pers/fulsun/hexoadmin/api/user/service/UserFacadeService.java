package pers.fulsun.hexoadmin.api.user.service;

import pers.fulsun.hexoadmin.api.user.request.UserQueryRequest;
import pers.fulsun.hexoadmin.api.user.request.UserRegisterRequest;
import pers.fulsun.hexoadmin.api.user.response.UserOperatorResponse;
import pers.fulsun.hexoadmin.api.user.response.UserQueryResponse;
import pers.fulsun.hexoadmin.api.user.response.data.UserInfo;

public interface UserFacadeService {
    
    /**
     * 用户信息查询
     *
     * @param userQueryRequest
     * @return
     */
    UserQueryResponse<UserInfo> query(UserQueryRequest userQueryRequest);
    
    /**
     * 用户注册
     *
     * @param userRegisterRequest
     * @return
     */
    UserOperatorResponse register(UserRegisterRequest userRegisterRequest);
    
    /**
     * 验证密码
     *
     * @param userId   用户ID
     * @param password 密码
     * @return true/false
     */
    boolean checkPassword(Long userId, String password);
}
