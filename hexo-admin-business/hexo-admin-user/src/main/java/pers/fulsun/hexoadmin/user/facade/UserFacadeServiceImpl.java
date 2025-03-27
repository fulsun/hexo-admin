package pers.fulsun.hexoadmin.user.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.fulsun.hexoadmin.api.user.request.UserQueryRequest;
import pers.fulsun.hexoadmin.api.user.request.UserRegisterRequest;
import pers.fulsun.hexoadmin.api.user.request.condition.UserIdQueryCondition;
import pers.fulsun.hexoadmin.api.user.request.condition.UserPhoneAndPasswordQueryCondition;
import pers.fulsun.hexoadmin.api.user.request.condition.UserPhoneQueryCondition;
import pers.fulsun.hexoadmin.api.user.response.UserOperatorResponse;
import pers.fulsun.hexoadmin.api.user.response.UserQueryResponse;
import pers.fulsun.hexoadmin.api.user.response.data.UserInfo;
import pers.fulsun.hexoadmin.api.user.service.UserFacadeService;
import pers.fulsun.hexoadmin.user.domain.entity.User;
import pers.fulsun.hexoadmin.user.domain.entity.convertor.UserConvertor;
import pers.fulsun.hexoadmin.user.domain.service.UserService;

@Service
public class UserFacadeServiceImpl implements UserFacadeService {
    
    @Autowired
    private UserService userService;
    
    @Override
    public UserQueryResponse<UserInfo> query(UserQueryRequest userQueryRequest) {
        User user = switch (userQueryRequest.getUserQueryCondition()) {
            case UserIdQueryCondition userIdQueryCondition:
                yield userService.findById(userIdQueryCondition.getUserId());
            case UserPhoneQueryCondition userPhoneQueryCondition:
                yield userService.findByTelephone(userPhoneQueryCondition.getTelephone());
            case UserPhoneAndPasswordQueryCondition userPhoneAndPasswordQueryCondition:
                yield userService.findByTelephoneAndPass(userPhoneAndPasswordQueryCondition.getTelephone(),
                        userPhoneAndPasswordQueryCondition.getPassword());
            default:
                throw new UnsupportedOperationException(
                        userQueryRequest.getUserQueryCondition() + "'' is not supported");
        };
        UserQueryResponse<UserInfo> response = new UserQueryResponse();
        response.setSuccess(true);
        response.setData(null);
        if (user != null) {
            response.setData(UserConvertor.INSTANCE.mapToVo(user));
        }
        response.setData(null);
        return response;
    }
    
    @Override
    public UserOperatorResponse register(UserRegisterRequest userRegisterRequest) {
        return userService.register(userRegisterRequest.getTelephone(), userRegisterRequest.getInviteCode());
    }
}
