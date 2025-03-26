package pers.fulsun.hexoadmin.auth.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.fulsun.hexoadmin.auth.vo.LoginParam;
import pers.fulsun.hexoadmin.auth.vo.LoginVO;
import pers.fulsun.hexoadmin.web.vo.Result;

@RestController
@RequestMapping("auth")
@Slf4j
public class AuthController {

    /**
     * 登录方法
     */
    @PostMapping("/login")
    public Result<LoginVO> login(@Valid @RequestBody LoginParam loginParam) {
        return Result.success(null);

    }

}
