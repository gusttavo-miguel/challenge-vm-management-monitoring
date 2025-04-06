package br.com.ustore.api.controller;

import br.com.ustore.api.dto.LoginRequest;
import br.com.ustore.api.dto.LoginResponse;
import br.com.ustore.api.entity.UserEntity;
import br.com.ustore.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        UserEntity user = userRepository.findByEmail(loginRequest.email());

        if (user == null) {
            return new LoginResponse("Usuário não encontrado.");
        }

        boolean passwordMatches = passwordEncoder.matches(loginRequest.password(), user.getPassword());
        if (!passwordMatches) {
            return new LoginResponse("Senha inválida.");
        }

        return new LoginResponse("Login realizado com sucesso!");
    }
}