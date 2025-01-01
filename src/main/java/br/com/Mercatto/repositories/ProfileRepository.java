package br.com.Mercatto.repositories;

import br.com.Mercatto.model.ProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileModel, Long> {
}
