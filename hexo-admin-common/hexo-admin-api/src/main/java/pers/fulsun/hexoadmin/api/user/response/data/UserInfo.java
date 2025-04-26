package pers.fulsun.hexoadmin.api.user.response.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserInfo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 用户Id
     */
    private Long userId;
    
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
}

