package com.eco.base.mapper;
import com.eco.base.dto.UserDTO;
import com.eco.base.entity.User;
import com.eco.base.entity.enums.RoleType;

public class UserMapper {
    public static User entityToDto (UserDTO dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .phoneNum(dto.getPhoneNum())
                .enabled(true)
                .roleType(RoleType.ADMIN)
                .build();
    }

    public static UserDTO dtoToEntity (User entity) {
        return UserDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .phoneNum(entity.getPhoneNum())
                .enabled(entity.isEnabled())
                .roleType(entity.getRoleType())
                .build();
    }
}
