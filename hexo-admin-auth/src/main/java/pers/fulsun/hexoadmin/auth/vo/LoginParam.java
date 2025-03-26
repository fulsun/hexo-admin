package pers.fulsun.hexoadmin.auth.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginParam extends RegisterParam {
    /**
     * 记住我
     */
    private Boolean rememberMe;
}
