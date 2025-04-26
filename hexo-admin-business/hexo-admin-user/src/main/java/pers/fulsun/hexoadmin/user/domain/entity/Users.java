package pers.fulsun.hexoadmin.user.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pers.fulsun.hexoadmin.datasource.domain.entity.BaseEntity;
import pers.fulsun.hexoadmin.user.infrastructure.util.AesUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表(users)实体类
 *
 * @author fulsun
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2025-04-26 15:41:19
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("users")
public class Users extends BaseEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 用户昵称
     */
    private String nickName;
    
    /**
     * 手机号码
     */
    private String telephone;
    
    /**
     * 密码哈希
     */
    private String passwordHash;
    
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
    
    public void register(String telephone, String nickName, String password) {
        this.telephone = telephone;
        this.nickName = nickName;
        this.passwordHash = AesUtil.encrypt(password);
    }
}