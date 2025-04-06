package br.com.ustore.api.controller;

import br.com.ustore.api.dto.LoginRequestDTO;
import br.com.ustore.api.dto.LoginResponseDTO;
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
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        UserEntity user = userRepository.findByEmail(loginRequestDTO.email());

        if (user == null) {
            return new LoginResponseDTO("Usuário não encontrado.");
        }

        boolean passwordMatches = passwordEncoder.matches(loginRequestDTO.password(), user.getPassword());
        if (!passwordMatches) {
            return new LoginResponseDTO("Senha inválida.");
        }

        return new LoginResponseDTO("Login realizado com sucesso!");
    }
}