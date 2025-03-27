package pers.fulsun.hexoadmin.user.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jakarta.validation.constraints.NotNull;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.fulsun.hexoadmin.user.domain.entity.User;

/**
 * 用户表 Mapper 接口
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    User findById(@Param("id") long id);

    /**
     * 根据手机号查询用户
     *
     * @param telephone
     * @return
     */
    User findByTelephone(@NotNull @Param("telephone") String telephone);

    /**
     * 根据昵称和密码查询用户
     *
     * @param telephone
     * @param passwordHash
     * @return
     */
    User findByTelephoneAndPass(@Param("telephone") String telephone, @Param("passwordHash") String passwordHash);

    /**
     * 根据昵称查询用户
     *
     * @param nickname
     * @return
     */
    User findByNickname(@NotNull @Param("nickName") String nickname);

    /**
     * 根据邀请码查询用户
     *
     * @param inviteCode
     * @return
     */
    User findByInviteCode(@NotNull @Param("inviteCode") String inviteCode);

}
