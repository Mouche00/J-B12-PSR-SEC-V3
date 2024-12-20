package org.yc.keycloak.utils.mappers.dtos;

import org.yc.keycloak.dtos.UserDTO;
import org.yc.keycloak.models.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // ResponseDTO
    @Mapping(target = "password", ignore = true)
    UserDTO toDTO(User user);

    // RequestDTO
    @Mapping(target = "id", ignore = true)
    User toUser(UserDTO userDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserDTO dto, @MappingTarget User entity);
}
