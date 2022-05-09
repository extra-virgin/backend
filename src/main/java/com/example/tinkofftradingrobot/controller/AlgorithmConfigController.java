package com.example.tinkofftradingrobot.controller;

import com.example.tinkofftradingrobot.config.AlgorithmConfigKeeper;
import com.example.tinkofftradingrobot.dto.AlgorithmConfig;
import com.example.tinkofftradingrobot.strategy.validator.AlgorithmConfigValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/algorithm")
public class AlgorithmConfigController {


    @PostMapping(value = "/configure")
    public ResponseEntity<?> configure(@RequestBody AlgorithmConfig algorithmConfig) {
        if (AlgorithmConfigValidator.isValid(algorithmConfig)) {
            AlgorithmConfigKeeper.setAlgorithmConfig(algorithmConfig);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
