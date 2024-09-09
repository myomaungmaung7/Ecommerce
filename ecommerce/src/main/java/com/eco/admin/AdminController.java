package com.eco.admin;

import com.eco.base.common.AUTHResponse;
import com.eco.base.dto.UserDTO;
import com.eco.base.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admins")
@RequiredArgsConstructor
@Tag(name = "ADMIN-CONTROLLER")
public class AdminController {

    private final UserService userService;

    @PostMapping("add-user")
    public ResponseEntity<AUTHResponse> addUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.addUser(userDTO));
    }
}
