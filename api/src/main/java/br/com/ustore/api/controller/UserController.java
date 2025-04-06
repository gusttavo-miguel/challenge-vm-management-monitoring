package br.com.ustore.api.controller;

import br.com.ustore.api.entity.UserModel;
import br.com.ustore.api.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/create")
    public String createUser (@Valid @RequestBody UserModel userModel) {
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userRepository.save(userModel);
        return "Usuário com e-mail " + userModel.getEmail() + " criado com sucesso!";
    }

    @GetMapping("/all")
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "Usuário excluído com sucesso!";
    }
}