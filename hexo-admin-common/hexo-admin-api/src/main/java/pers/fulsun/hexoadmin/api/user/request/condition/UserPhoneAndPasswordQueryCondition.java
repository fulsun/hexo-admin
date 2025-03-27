package pers.fulsun.hexoadmin.api.user.request.condition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPhoneAndPasswordQueryCondition implements UserQueryCondition {
    private static final long serialVersionUID = 1L;
    private String telephone;
    private String password;

}
