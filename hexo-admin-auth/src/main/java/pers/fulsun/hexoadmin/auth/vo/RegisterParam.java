package pers.fulsun.hexoadmin.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "注册参数")
public class RegisterParam {
    
    /**
     * 手机号
     */
    @Schema(description = "手机号")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "手机号格式不正确")
    private String telephone;
    
    /**
     * 验证码
     */
    @Schema(description = "验证码")
    @NotBlank(message = "密码不能为空")
    private String password;
    
    /**
     * 验证码
     */
    @Schema(description = "验证码")
    @NotBlank(message = "验证码不能为空")
    private String captcha;
    
    /**
     * 邀请码
     */
    @Schema(description = "邀请码")
    private String inviteCode;
}
