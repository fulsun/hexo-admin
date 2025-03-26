package pers.fulsun.hexoadmin.api.user.constant;

/**
 * 用户状态
 */
public enum UserStateEnum {
    /**
     * 创建成功
     */
    INIT,
    /**
     * 激活
     */
    ACTIVE,
    /**
     * 实名认证
     */
    AUTH,

    /**
     * 冻结
     */
    FROZEN,

    /**
     * 注销
     */
    Deregistration;

}
