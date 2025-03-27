package pers.fulsun.hexoadmin.auth.vo;

import cn.hutool.extra.tokenizer.TokenizerUtil;
import lombok.Data;
import pers.fulsun.hexoadmin.api.user.response.data.UserInfo;

import java.io.Serializable;
@Data
public class LoginVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户标识，如用户ID
     */
    private String userId;
    /**
     * 访问令牌
     */
    private String token;

    /**
     * 令牌过期时间
     */
    private Long tokenExpiration;


    public LoginVO(UserInfo userInfo) {
        this.userId = userInfo.getUserId().toString();
    }
}
