package pers.fulsun.hexoadmin.auth.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.fulsun.hexoadmin.api.user.request.UserQueryRequest;
import pers.fulsun.hexoadmin.api.user.request.UserRegisterRequest;
import pers.fulsun.hexoadmin.api.user.response.UserOperatorResponse;
import pers.fulsun.hexoadmin.api.user.response.UserQueryResponse;
import pers.fulsun.hexoadmin.api.user.response.data.UserInfo;
import pers.fulsun.hexoadmin.api.user.service.UserFacadeService;
import pers.fulsun.hexoadmin.auth.vo.LoginParam;
import pers.fulsun.hexoadmin.auth.vo.LoginVO;
import pers.fulsun.hexoadmin.web.vo.Result;

@RestController
@RequestMapping("auth")
@Slf4j
public class AuthController {
    @Autowired
    private UserFacadeService userFacadeService;

    /**
     * 登录方法
     */
    @PostMapping("/login")
    public Result<LoginVO> login(@Valid @RequestBody LoginParam loginParam) {
        // 判断是注册还是登陆
        // 查询用户信息
        UserQueryRequest userQueryRequest = new UserQueryRequest(loginParam.getTelephone());
        UserQueryResponse<UserInfo> userQueryResponse = userFacadeService.query(userQueryRequest);
        UserInfo userInfo = userQueryResponse.getData();
        if (userInfo == null) {
            // 需要注册
            UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
            userRegisterRequest.setTelephone(loginParam.getTelephone());
            userRegisterRequest.setInviteCode(loginParam.getInviteCode());

            UserOperatorResponse response = userFacadeService.register(userRegisterRequest);
            if (response.getSuccess()) {
                userQueryResponse = userFacadeService.query(userQueryRequest);
                userInfo = userQueryResponse.getData();
                LoginVO loginVO = new LoginVO(userInfo);
                return Result.success(loginVO);
            }
            return Result.error(response.getResponseCode(), response.getResponseMessage());
        } else {
            // 登录
            LoginVO loginVO = new LoginVO(userInfo);
            return Result.success(loginVO);
        }
    }

}
