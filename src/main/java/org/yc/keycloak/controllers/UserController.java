package org.yc.keycloak.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.yc.keycloak.dtos.UserDTO;
import org.yc.keycloak.services.UserService;
import org.yc.keycloak.utils.ApiResponse;
import org.yc.keycloak.utils.ResponseUtil;
import org.yc.keycloak.utils.validation.OnCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDTO>> registerUser(@RequestBody @Validated(OnCreate.class) UserDTO user, HttpServletRequest request) {
        UserDTO registeredUser = userService.register(user);
        return ResponseEntity.ok(ResponseUtil.success(registeredUser, "User registered successfully", request.getRequestURI()));
    }

    @PutMapping("/update-role/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> updateRole(@PathVariable String id, @RequestBody @Valid UserDTO user, HttpServletRequest request) {
        UserDTO registeredUser = userService.updateRole(id, user);
        return ResponseEntity.ok(ResponseUtil.success(registeredUser, "User role updated successfully", request.getRequestURI()));
    }
}