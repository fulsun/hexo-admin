package pers.fulsun.hexoadmin.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "登录参数")
public class LoginParam extends RegisterParam {
    
    /**
     * 记住我
     */
    @Schema(description = "记住我")
    private Boolean rememberMe;
}
