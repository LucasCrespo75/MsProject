package com.br.user.controller;


import com.br.user.dtos.UserRecordDto;
import com.br.user.model.UserModel;
import com.br.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {


    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/users")
    public ResponseEntity<UserModel> criandoUsers(@Valid @RequestBody UserRecordDto userRecordDto) {
        var userModel = new UserModel();
        //Transferindo de DTO para model
        BeanUtils.copyProperties(userRecordDto, userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
    }





}
