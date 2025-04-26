package pers.fulsun.hexoadmin.auth.controller;

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
import pers.fulsun.hexoadmin.api.user.constant.UserStateEnum;
import pers.fulsun.hexoadmin.api.user.request.UserQueryRequest;
import pers.fulsun.hexoadmin.api.user.response.UserQueryResponse;
import pers.fulsun.hexoadmin.api.user.response.data.UserInfo;
import pers.fulsun.hexoadmin.api.user.service.UserFacadeService;
import pers.fulsun.hexoadmin.auth.exception.AuthErrorCode;
import pers.fulsun.hexoadmin.auth.vo.LoginParam;
import pers.fulsun.hexoadmin.auth.vo.LoginVO;
import pers.fulsun.hexoadmin.base.exception.BizException;
import pers.fulsun.hexoadmin.user.infrastructure.exception.UserErrorCode;
import pers.fulsun.hexoadmin.user.infrastructure.exception.UserException;
import pers.fulsun.hexoadmin.web.vo.Result;

@Tag(name = "auth", description = "登录认证管理")
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
                throw new UserException(UserErrorCode.USER_NOT_EXIST);
            } else {
                if (userInfo.getState().equals(UserStateEnum.DISABLE.getCode())) {
                    // 账号被禁用
                    throw new UserException(UserErrorCode.USER_DISABLED);
                } else if (userInfo.getState().equals(UserStateEnum.Deregistration.getCode())) {
                    // 账号被注销
                    throw new UserException(UserErrorCode.USER_DEREGISTRATION);
                } else {
                    // 校验密码
                    Long userId = userInfo.getUserId();
                    if (userFacadeService.checkPassword(userId, loginParam.getPassword())) {
                        // 登录
                        StpUtil.login(userId);
                        String token = StpUtil.getTokenValue();
                        LoginVO loginVO = new LoginVO(userInfo);
                        loginVO.setToken(token);
                        return Result.success(loginVO);
                    } else {
                        throw new UserException(UserErrorCode.USERNAME_OR_PASSWORD_ERROR);
                    }
                    
                }
            }
        } catch (BizException e) {
            log.error("登录失败", e);
            throw e;
        } catch (Exception e) {
            log.error("登录失败", e);
            throw new BizException(AuthErrorCode.USER_LOGIN_FAILED);
        }
        
    }
    
    
    @PostMapping("/logout")
    
    public Result<Boolean> logout() {
        if (StpUtil.isLogin()) {
            StpUtil.logout();
        }
        return Result.success(true);
    }
}
