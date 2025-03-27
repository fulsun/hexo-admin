package pers.fulsun.hexoadmin.api.user.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.fulsun.hexoadmin.api.user.request.condition.UserPhoneQueryCondition;
import pers.fulsun.hexoadmin.api.user.request.condition.UserQueryCondition;
import pers.fulsun.hexoadmin.base.request.BaseRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryRequest extends BaseRequest {
    
    private UserQueryCondition userQueryCondition;
    
    public UserQueryRequest(String telephone) {
        UserPhoneQueryCondition userPhoneQueryCondition = new UserPhoneQueryCondition();
        userPhoneQueryCondition.setTelephone(telephone);
        this.userQueryCondition = userPhoneQueryCondition;
    }
    
    
}