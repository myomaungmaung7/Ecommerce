package com.eco.base.service;

import com.eco.base.common.AUTHResponse;
import com.eco.base.common.Constant;
import com.eco.base.dto.UserDTO;
import com.eco.base.entity.User;
import com.eco.base.entity.enums.RoleType;
import com.eco.base.mapper.UserMapper;
import com.eco.base.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AUTHResponse addUser(UserDTO userDTO) {
        User user = new User();
        user = userRepository.save(User.builder()
                        .name(userDTO.getName())
                        .enabled(true)
                        .email(userDTO.getEmail())
                        .password(passwordEncoder.encode(userDTO.getPassword()))
                        .phoneNum(userDTO.getPhoneNum())
                        .roleType(RoleType.ADMIN)
                .build());
        return AUTHResponse.success(Constant.USER_ADDED, UserMapper.dtoToEntity(user));
    }
}
