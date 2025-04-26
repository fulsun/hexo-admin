package pers.fulsun.hexoadmin.auth.vo;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import pers.fulsun.hexoadmin.api.user.response.data.UserInfo;

import java.io.Serializable;
import java.util.Date;

@Data
@Schema(description = "登录返回值")
public class LoginVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 用户标识，如用户ID
     */
    @Schema(description = "用户标识，如用户ID")
    private Long userId;
    
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
    
    
    /**
     * 用户昵称
     */
    private String nickName;
    
    /**
     * 手机号码
     */
    private String telephone;
    
    
    /**
     * 用户状态（ACTIVE，FROZEN）
     */
    private String state;
    
    /**
     * 最后登录时间
     */
    private Date lastLoginTime;
    
    /**
     * 用户角色
     */
    private String userRole;
    
    public LoginVO(UserInfo userInfo) {
        this.userId = userInfo.getUserId();
        this.nickName = userInfo.getNickName();
        this.telephone = userInfo.getTelephone();
        this.state = userInfo.getState();
        this.lastLoginTime = userInfo.getLastLoginTime();
        this.userRole = userInfo.getUserRole();
        this.token = StpUtil.getTokenValue();
        this.tokenExpiration = StpUtil.getTokenTimeout();
    }
}
