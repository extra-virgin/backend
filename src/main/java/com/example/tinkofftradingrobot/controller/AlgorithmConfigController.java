package com.example.tinkofftradingrobot.controller;

import com.example.tinkofftradingrobot.dto.AlgorithmConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AlgorithmConfigController {


    public AlgorithmConfigController() {

    }

    @PostMapping(value = "/configure")
    public ResponseEntity<?> configure(@RequestBody AlgorithmConfig algorithmConfig) {
        return null;
    }

}
