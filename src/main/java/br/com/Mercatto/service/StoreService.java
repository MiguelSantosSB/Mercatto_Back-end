package br.com.Mercatto.service;

import br.com.Mercatto.model.StoreModel;

import java.util.List;

public interface StoreService {

    List<StoreModel> findAll();

    StoreModel findById(Long id);

    StoreModel save(StoreModel model);

    StoreModel update(Long id, StoreModel model);

    Long delete(Long id);

}
