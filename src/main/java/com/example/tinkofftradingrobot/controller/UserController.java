package com.example.tinkofftradingrobot.controller;

import com.example.tinkofftradingrobot.dto.UserDTO;
import com.example.tinkofftradingrobot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        if (userDTO.getToken() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        var userOpt = userService.addUser(userDTO);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        } else {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody UserDTO userDTO) {
        if (userDTO.getToken() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        var userOpt = userService.addUser(userDTO);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        } else {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}
