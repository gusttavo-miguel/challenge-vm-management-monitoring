package br.com.ustore.api.controller;

import br.com.ustore.api.dto.MessageDTO;
import br.com.ustore.api.dto.UserDTO;
import br.com.ustore.api.entity.UserEntity;
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
    public MessageDTO createUser(@Valid @RequestBody UserDTO userDTO) {

        UserEntity userEntity = new UserEntity(userDTO.email(), userDTO.password());
        UserEntity user = userRepository.findByEmail(userEntity.getEmail());

        if (user == null) {
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            userRepository.save(userEntity);
            return new MessageDTO("Usuário com e-mail " + userEntity.getEmail() + " criado com sucesso!");
        }

        return new MessageDTO("Usuário com e-mail " + userEntity.getEmail() + " já está cadastrado!");
    }

    @PutMapping("/update-password")
    public MessageDTO updatePassword(@RequestBody UserDTO userDTO) {

        UserEntity userEntity = userRepository.findByEmail(userDTO.email());
        UserEntity user = userRepository.findByEmail(userEntity.getEmail());

        if (user != null) {
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            userRepository.save(userEntity);
            return new MessageDTO("Senha atualizada com sucesso!");
        }

        return new MessageDTO("Usuário não encontrado.");

    }


    @GetMapping("/all")
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public MessageDTO deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return new MessageDTO("Usuário excluído com sucesso!");
    }
}