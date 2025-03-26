package pers.fulsun.hexoadmin.api.user.request;

import pers.fulsun.hexoadmin.api.user.request.condition.UserPhoneQueryCondition;
import pers.fulsun.hexoadmin.api.user.request.condition.UserQueryCondition;
import pers.fulsun.hexoadmin.base.request.BaseRequest;

public class UserQueryRequest extends BaseRequest {
    private UserQueryCondition userQueryCondition;

    public UserQueryRequest(String telephone) {
        UserPhoneQueryCondition userPhoneQueryCondition = new UserPhoneQueryCondition();
        userPhoneQueryCondition.setTelephone(telephone);
        this.userQueryCondition = userPhoneQueryCondition;
    }
}