package br.com.Mercatto.repositories;

import br.com.Mercatto.model.StoreModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreModel, Long> {
}
