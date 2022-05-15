package com.example.tinkofftradingrobot.controller;

import com.example.tinkofftradingrobot.dto.UserDTO;
import com.example.tinkofftradingrobot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.tinkofftradingrobot.config.security.ApiAuthenticationToken.getApiAuthTokenFromContext;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestParam(name = "is_sandbox", required = false, defaultValue = "false") Boolean isSandbox) {
        var auth = getApiAuthTokenFromContext();
        if (auth.getName() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var userOpt = userService.addUser(new UserDTO(auth.getName()), isSandbox);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        } else {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser() {
        var auth = getApiAuthTokenFromContext();
        if (auth.getName() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var userOpt = userService.removeUser(new UserDTO(auth.getName()));
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User is not present");
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
