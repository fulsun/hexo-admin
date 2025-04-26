package pers.fulsun.hexoadmin.user.domain.entity.convertor;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;
import pers.fulsun.hexoadmin.api.user.response.data.UserInfo;
import pers.fulsun.hexoadmin.user.domain.entity.Users;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserConvertor {
    
    UserConvertor INSTANCE = Mappers.getMapper(UserConvertor.class);
    
    /**
     * 转换为vo
     *
     * @param request
     * @return
     */
    @Mapping(target = "userId", source = "request.id")
    public UserInfo mapToVo(Users request);
    
    /**
     * 转换为实体
     *
     * @param request
     * @return
     */
    @Mapping(target = "id", source = "request.userId")
    public Users mapToEntity(UserInfo request);
    
    /**
     * 转换为VO
     *
     * @param request
     * @return
     */
    @Mapping(target = "id", source = "request.userId")
    public List<UserInfo> mapToVo(List<Users> request);
}
