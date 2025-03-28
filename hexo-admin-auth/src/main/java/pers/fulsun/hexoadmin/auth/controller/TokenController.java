package pers.fulsun.hexoadmin.auth.controller;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.fulsun.hexoadmin.auth.exception.AuthErrorCode;
import pers.fulsun.hexoadmin.auth.exception.AuthException;
import pers.fulsun.hexoadmin.web.vo.Result;

import java.util.UUID;

@RestController
@RequestMapping("token")
@Tag(name = "auth", description = "token相关接口")
public class TokenController {
    
    private static final String TOKEN_PREFIX = "token:";
    
    /**
     * 获取token.
     *
     * @param scene 场景
     * @return token
     */
    @GetMapping("/get")
    public Result<String> get(@NotBlank String scene) {
        if (StpUtil.isLogin()) {
            String token = UUID.randomUUID().toString();
            String tokenKey = TOKEN_PREFIX + scene + token;
            return Result.success(tokenKey);
        }
        throw new AuthException(AuthErrorCode.USER_NOT_LOGIN);
    }
}
