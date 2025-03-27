package pers.fulsun.hexoadmin.user.domain.entity;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import pers.fulsun.hexoadmin.api.user.constant.UserRole;
import pers.fulsun.hexoadmin.api.user.constant.UserStateEnum;
import pers.fulsun.hexoadmin.datasource.domain.entity.BaseEntity;
import pers.fulsun.hexoadmin.user.infrastructure.mapper.AesEncryptTypeHandler;

import java.util.Date;

@Setter
@Getter
@TableName("users")
public class User extends BaseEntity {
    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String passwordHash;

    /**
     * 状态
     */
    private UserStateEnum state;

    /**
     * 邀请码
     */
    private String inviteCode;

    /**
     * 邀请人用户ID
     */
    private String inviterId;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 头像地址
     */
    private String profilePhotoUrl;

    /**
     * 区块链地址
     */
    private String blockChainUrl;

    /**
     * 区块链平台
     */
    private String blockChainPlatform;

    /**
     * 实名认证
     */
    private Boolean certification;

    /**
     * 真实姓名
     */
    @TableField(typeHandler = AesEncryptTypeHandler.class)
    private String realName;

    /**
     * 身份证hash
     */
    @TableField(typeHandler = AesEncryptTypeHandler.class)
    private String idCardNo;

    /**
     * 用户角色
     */
    private UserRole userRole;

    public User register(String telephone, String nickName, String password, String inviteCode, String inviterId) {
        this.setTelephone(telephone);
        this.setNickName(nickName);
        this.setPasswordHash(DigestUtil.md5Hex(password));
        this.setState(UserStateEnum.INIT);
        this.setUserRole(UserRole.CUSTOMER);
        this.setInviteCode(inviteCode);
        this.setInviterId(inviterId);
        return this;
    }
}
