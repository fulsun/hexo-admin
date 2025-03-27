package pers.fulsun.hexoadmin.auth.vo;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import pers.fulsun.hexoadmin.api.user.response.data.UserInfo;

import java.io.Serializable;

@Data
@Schema(description = "登录返回值")
public class LoginVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 用户标识，如用户ID
     */
    @Schema(description = "用户标识，如用户ID")
    private String userId;
    
    /**
     * 访问令牌
     */
    @Schema(description = "访问令牌")
    private String token;
    
    /**
     * 令牌过期时间
     */
    @Schema(description = "令牌过期时间")
    private Long tokenExpiration;
    
    public LoginVO(UserInfo userInfo) {
        this.userId = userInfo.getUserId().toString();
        this.token = StpUtil.getTokenValue();
        this.tokenExpiration = StpUtil.getTokenSessionTimeout();
    }
}
