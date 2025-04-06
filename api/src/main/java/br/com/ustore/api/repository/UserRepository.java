package br.com.ustore.api.repository;

import br.com.ustore.api.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByEmail(String email);
}