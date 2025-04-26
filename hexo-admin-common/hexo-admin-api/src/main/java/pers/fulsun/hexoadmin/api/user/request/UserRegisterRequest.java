package pers.fulsun.hexoadmin.api.user.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.fulsun.hexoadmin.base.request.BaseRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest extends BaseRequest {
    
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "手机号格式不正确")
    private String telephone;
    
    @NotBlank(message = "密码不能为空")
    private String password;
    
}
