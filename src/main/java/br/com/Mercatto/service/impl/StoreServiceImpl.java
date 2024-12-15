package br.com.Mercatto.service.impl;

import br.com.Mercatto.model.StoreModel;
import br.com.Mercatto.repositories.StoreRepository;
import br.com.Mercatto.service.StoreService;
import jakarta.persistence.NoResultException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    public List<StoreModel> findAll() {
        return storeRepository.findAll();
    }

    @Override
    public StoreModel findById(Long id) {
        return storeRepository.findById(id).orElseThrow(() -> new NoResultException("Loja não encontrada."));
    }

    @Override
    public StoreModel save(StoreModel model) {
        return storeRepository.save(model);
    }

    @Override
    public StoreModel update(Long id, StoreModel model) {
        storeRepository.findById(id).orElseThrow(() -> new NoResultException("Loja não encontrada."));
        model.setStoreId(id);
        storeRepository.save(model);
        return model;
    }

    @Override
    public Long delete(Long id) {
        var response = storeRepository.findById(id).orElseThrow(() -> new NoResultException("Loja não encontrada."));
        storeRepository.delete(response);
        return id;
    }
}
