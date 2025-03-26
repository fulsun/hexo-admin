package pers.fulsun.hexoadmin.api.user.request.condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPhoneQueryCondition implements UserQueryCondition {
    private static final long serialVersionUID = 1L;

    /**
     * 用户手机号
     */
    private String telephone;
}
