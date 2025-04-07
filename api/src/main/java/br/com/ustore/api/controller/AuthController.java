package br.com.ustore.api.controller;

import br.com.ustore.api.dto.LoginRequestDTO;
import br.com.ustore.api.dto.LoginResponseDTO;
import br.com.ustore.api.dto.MessageDTO;
import br.com.ustore.api.entity.UserEntity;
import br.com.ustore.api.repository.UserRepository;
import br.com.ustore.api.security.JwtUtil;
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

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequest) {
        UserEntity user = userRepository.findByEmail(loginRequest.email());

        if (user == null) {
            return new LoginResponseDTO("Usuário não cadastrado!");
        } else if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            return new LoginResponseDTO("Credenciais inválidas");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponseDTO(token);
    }
}