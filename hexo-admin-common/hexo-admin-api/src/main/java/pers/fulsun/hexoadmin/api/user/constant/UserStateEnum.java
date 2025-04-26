package pers.fulsun.hexoadmin.api.user.constant;

/**
 * 用户状态
 */
public enum UserStateEnum {
    
    NORMAL("1", "正常"),
    AUTHENTICATED("2", "实名认证"),
    FROZEN("3", "冻结"),
    DISABLE("4", "禁用"),
    Deregistration("4", "注销");
    
    private String code;
    
    private String message;
    
    UserStateEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getMessage() {
        return message;
    }
    
    public UserStateEnum getUserStateEnum(String code) {
        for (UserStateEnum userStateEnum : UserStateEnum.values()) {
            if (userStateEnum.getCode().equals(code)) {
                return userStateEnum;
            }
        }
        return null;
    }
    
}
