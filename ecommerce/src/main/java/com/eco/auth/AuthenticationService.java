package com.eco.auth;

import com.eco.base.common.AUTHResponse;
import com.eco.base.common.Constant;
import com.eco.base.config.IJwtService;
import com.eco.base.entity.Token;
import com.eco.base.entity.User;
import com.eco.base.entity.enums.RoleType;
import com.eco.base.entity.enums.TokenType;
import com.eco.base.mapper.UserMapper;
import com.eco.base.repository.TokenRepository;
import com.eco.base.repository.UserRepository;
import com.eco.base.utils.DateUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final IJwtService iJwtService;
    private final AuthenticationManager authenticationManager;

    public AUTHResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail());
        if (user == null || !user.isEnabled()) {
            return AUTHResponse.fail(Constant.USER_NOT_FOUND, "User is enabled or not found");
        }
        var jwtToken = iJwtService.generateToken(user);
        var refreshToken = iJwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AUTHResponse.success(Constant.AUTH_SUCCESS, AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build());
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .loginAt(DateUtils.getNowDate())
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
            token.setLogoutAt(DateUtils.getNowDate());
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = iJwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail);
            if (iJwtService.isTokenValid(refreshToken, user)) {
                var accessToken = iJwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    public AUTHResponse getAll() {
        List<User> users = userRepository.findAll();
        return ObjectUtils.isEmpty(users)?
                AUTHResponse.fail("Fail!"):
                AUTHResponse.success("Success", users);
    }

    public AUTHResponse register(RegistrationRequest request) {
        if (userRepository.existsByEmailAndPhoneNum(request.getEmail(), request.getPhoneNum())) {
            return AUTHResponse.fail(Constant.USER_REGISTERED);
        }
        User user = userRepository.save(User.builder()
                        .name(request.getName())
                        .email(request.getEmail())
                        .phoneNum(request.getPhoneNum())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .roleType(RoleType.CUSTOMER)
                        .enabled(true)
                .build());
        return AUTHResponse.success(Constant.USER_REGISTER_SUCCESS, UserMapper.entityToDto(user));
    }
}
