package com.br.email.service;

import com.br.email.model.UserModel;
import com.br.email.respository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;


    }

    @Transactional
    public UserModel save(UserModel user) {
        return userRepository.save(user);

    }

    public List<UserModel> listarTodos(){
        return userRepository.findAll();
    }

}
