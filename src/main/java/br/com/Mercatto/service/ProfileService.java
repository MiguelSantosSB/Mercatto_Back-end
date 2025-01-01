package br.com.Mercatto.service;

import br.com.Mercatto.model.ProfileModel;

import java.util.List;

public interface ProfileService {

    List<ProfileModel> findAll();

    ProfileModel findById(Long id);

    ProfileModel save(ProfileModel profileModel);

    ProfileModel update(Long id, ProfileModel profileModel);

    Long delete(Long id);
}
