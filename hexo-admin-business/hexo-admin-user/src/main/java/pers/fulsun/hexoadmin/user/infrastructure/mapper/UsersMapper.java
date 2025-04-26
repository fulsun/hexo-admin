package pers.fulsun.hexoadmin.user.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jakarta.validation.constraints.NotNull;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.fulsun.hexoadmin.user.domain.entity.Users;

/**
 * 用户信息表(users)数据Mapper
 *
 * @author fulsun
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2025-04-26 15:41:19
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {
    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    Users findById(@Param("id") long id);

    /**
     * 根据手机号查询用户
     *
     * @param telephone
     * @return
     */
    Users findByTelephone(@NotNull @Param("telephone") String telephone);

    /**
     * 根据昵称和密码查询用户
     *
     * @param telephone
     * @param passwordHash
     * @return
     */
    Users findByTelephoneAndPass(@Param("telephone") String telephone, @Param("passwordHash") String passwordHash);

    /**
     * 根据昵称查询用户
     *
     * @param nickname
     * @return
     */
    Users findByNickname(@NotNull @Param("nickName") String nickname);
}
