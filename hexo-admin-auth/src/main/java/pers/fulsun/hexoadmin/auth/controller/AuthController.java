package pers.fulsun.hexoadmin.auth.controller;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import pers.fulsun.hexoadmin.base.exception.BizException;
import pers.fulsun.hexoadmin.user.infrastructure.exception.UserErrorCode;
import pers.fulsun.hexoadmin.web.vo.Result;

@Tag(name = "auth", description = "后台授权管理")
@RestController
@RequestMapping("auth")
@Slf4j
public class AuthController {
    
    /**
     * 默认登录超时时间：7天
     */
    private static final Integer DEFAULT_LOGIN_SESSION_TIMEOUT = 60 * 60 * 24 * 7;
    
    @Autowired
    private UserFacadeService userFacadeService;
    
    /**
     * 登录方法
     */
    @Operation(summary = "用户登录", description = "用户登录")
    @ApiResponse(responseCode = "200", description = "登录成功", content = @Content(schema = @Schema(implementation = Result.class)))
    @PostMapping("/login")
    public Result<LoginVO> login(@Valid @RequestBody LoginParam loginParam) {
        try {
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
                    StpUtil.login(userInfo.getUserId(),
                            new SaLoginModel().setIsLastingCookie(loginParam.getRememberMe())
                                    .setTimeout(DEFAULT_LOGIN_SESSION_TIMEOUT));
                    StpUtil.getSession().set(userInfo.getUserId().toString(), userInfo);
                    LoginVO loginVO = new LoginVO(userInfo);
                    return Result.success(loginVO);
                }
                return Result.error(response.getResponseCode(), response.getResponseMessage());
            } else {
                // 登录
                StpUtil.login(userInfo.getUserId(), new SaLoginModel().setIsLastingCookie(loginParam.getRememberMe())
                        .setTimeout(DEFAULT_LOGIN_SESSION_TIMEOUT));
                StpUtil.getSession().set(userInfo.getUserId().toString(), userInfo);
                LoginVO loginVO = new LoginVO(userInfo);
                return Result.success(loginVO);
            }
        } catch (BizException e) {
            log.error("登录失败", e);
            throw e;
        } catch (Exception e) {
            log.error("登录失败", e);
            throw new BizException(UserErrorCode.USER_OPERATE_FAILED);
        }
        
    }
    
    @PostMapping("/logout")
    public Result<Boolean> logout() {
        StpUtil.logout();
        return Result.success(true);
    }
}
