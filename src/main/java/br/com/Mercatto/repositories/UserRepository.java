package br.com.Mercatto.repositories;

import br.com.Mercatto.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    boolean existsByEmail(String email);

    boolean existsByNumber(String number);

    Optional<UserModel> findByEmail(String email);
}
