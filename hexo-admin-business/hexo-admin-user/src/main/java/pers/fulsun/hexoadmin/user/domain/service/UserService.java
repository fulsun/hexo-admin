package pers.fulsun.hexoadmin.user.domain.service;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.fulsun.hexoadmin.api.user.response.UserOperatorResponse;
import pers.fulsun.hexoadmin.user.domain.entity.User;
import pers.fulsun.hexoadmin.user.infrastructure.exception.UserErrorCode;
import pers.fulsun.hexoadmin.user.infrastructure.exception.UserException;
import pers.fulsun.hexoadmin.user.infrastructure.mapper.UserMapper;

import static pers.fulsun.hexoadmin.user.infrastructure.exception.UserErrorCode.DUPLICATE_TELEPHONE_NUMBER;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    @Autowired
    private UserMapper userMapper;
    private static final String DEFAULT_NICK_NAME_PREFIX = "读者_";

    public User findById(Long userId) {
        return userMapper.findById(userId);
    }

    public User findByTelephone(String telephone) {
        return userMapper.findByTelephone(telephone);
    }

    public User findByTelephoneAndPass(String telephone, String password) {
        return userMapper.findByTelephoneAndPass(telephone, DigestUtil.md5Hex(password));
    }

    public UserOperatorResponse register(String telephone, String inviteCode) {
        String defaultNickName;
        String randomString;
        do {
            randomString = RandomUtil.randomString(6).toUpperCase();
            // 前缀 + 6位随机数 + 手机号后四位
            defaultNickName = DEFAULT_NICK_NAME_PREFIX + randomString + telephone.substring(7, 11);
        } while (nickNameExist(defaultNickName) & inviteCodeExist(randomString));

        String inviterId = null;
        if (StringUtils.isNotBlank(inviteCode)) {
            User inviter = userMapper.findByInviteCode(inviteCode);
            if (inviter != null) {
                inviterId = inviter.getId().toString();
            }
        }

        User user = register(telephone, defaultNickName, telephone, randomString, inviterId);
        Assert.notNull(user, UserErrorCode.USER_OPERATE_FAILED.getCode());

        UserOperatorResponse userOperatorResponse = new UserOperatorResponse();
        userOperatorResponse.setSuccess(true);

        return userOperatorResponse;
    }

    private User register(String telephone, String nickName, String password, String inviteCode, String inviterId) {
        if (userMapper.findByTelephone(telephone) != null) {
            throw new UserException(DUPLICATE_TELEPHONE_NUMBER);
        }

        User user = new User();
        user.register(telephone, nickName, password, inviteCode, inviterId);
        if (save(user)) {
            return userMapper.findByTelephone(telephone);
        }
        return null;
    }


    public boolean inviteCodeExist(String inviteCode) {
        return userMapper.findByInviteCode(inviteCode) != null;
    }

    public boolean nickNameExist(String nickName) {
        return userMapper.findByNickname(nickName) != null;
    }
}
