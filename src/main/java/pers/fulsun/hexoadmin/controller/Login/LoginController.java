package pers.fulsun.hexoadmin.controller.Login;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.fulsun.hexoadmin.request.LoginRequest;
import pers.fulsun.hexoadmin.vo.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录控制台
 *
 * @author fulsun
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest loginRequest) {
        Map<String, Object> result = new HashMap();
        Map<String, Object> userInfo = new HashMap();
        userInfo.put("name", loginRequest.getUsername());
        userInfo.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        userInfo.put("roles", new String[]{"admin"});

        result.put("token", loginRequest.getUsername() + ":" + loginRequest.getPassword());
        result.put("userInfo", userInfo);
        return Result.success(result);
    }
}
