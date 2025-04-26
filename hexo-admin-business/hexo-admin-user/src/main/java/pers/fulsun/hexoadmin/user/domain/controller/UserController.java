package pers.fulsun.hexoadmin.user.domain.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.fulsun.hexoadmin.api.user.request.UserRegisterRequest;
import pers.fulsun.hexoadmin.api.user.response.UserOperatorResponse;
import pers.fulsun.hexoadmin.api.user.service.UserFacadeService;
import pers.fulsun.hexoadmin.web.vo.Result;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserFacadeService userFacadeService;
    
    /**
     * 用户注册
     *
     * @return 注册请求
     */
    @PostMapping("/register")
    public Result<String> register(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        UserOperatorResponse response = userFacadeService.register(userRegisterRequest);
        if (response.getSuccess()) {
            return Result.success("注册成功");
        }
        return Result.error(response.getResponseCode(), response.getResponseMessage());
    }
    
}
