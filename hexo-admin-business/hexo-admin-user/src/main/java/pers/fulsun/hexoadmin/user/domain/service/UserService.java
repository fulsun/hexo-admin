package pers.fulsun.hexoadmin.user.domain.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.fulsun.hexoadmin.api.user.constant.UserStateEnum;
import pers.fulsun.hexoadmin.api.user.response.UserOperatorResponse;
import pers.fulsun.hexoadmin.user.domain.entity.Users;
import pers.fulsun.hexoadmin.user.infrastructure.exception.UserErrorCode;
import pers.fulsun.hexoadmin.user.infrastructure.exception.UserException;
import pers.fulsun.hexoadmin.user.infrastructure.mapper.UsersMapper;

import static pers.fulsun.hexoadmin.user.infrastructure.exception.UserErrorCode.USER_EXIST;


@Service
public class UserService extends ServiceImpl<UsersMapper, Users> {

    @Autowired
    private UsersMapper userMapper;

    private static final String DEFAULT_NICK_NAME_PREFIX = "读者_";
    
    public Users findById(Long userId) {
        return userMapper.findById(userId);
    }
    
    public Users findByTelephone(String telephone) {
        return userMapper.findByTelephone(telephone);
    }
    
    public Users findByTelephoneAndPass(String telephone, String password) {
        return userMapper.findByTelephoneAndPass(telephone, DigestUtil.md5Hex(password));
    }
    
    public UserOperatorResponse register(String telephone, String password) {
        if (userMapper.findByTelephone(telephone) != null) {
            throw new UserException(USER_EXIST);
        }
        Users user = new Users();
        user.register(telephone, getDefaultNickName(telephone), password);
        user.setState(UserStateEnum.NORMAL.getCode());
        if (save(user)) {
            user = userMapper.findByTelephone(telephone);
            if (user.getId() == null) {
                throw new UserException(UserErrorCode.USER_OPERATE_FAILED);
            }
        }
        UserOperatorResponse userOperatorResponse = new UserOperatorResponse();
        userOperatorResponse.setSuccess(true);
        
        return userOperatorResponse;
    }
    
    private String getDefaultNickName(String telephone) {
        String defaultNickName;
        String randomString;
        // 默认昵称生成
        do {
            randomString = RandomUtil.randomString(6).toUpperCase();
            // 前缀 + 6位随机数 + 手机号后四位
            defaultNickName = DEFAULT_NICK_NAME_PREFIX + randomString + telephone.substring(7, 11);
        } while (nickNameExist(defaultNickName));
        return defaultNickName;
    }
    
    public boolean nickNameExist(String nickName) {
        return userMapper.findByNickname(nickName) != null;
    }
}
