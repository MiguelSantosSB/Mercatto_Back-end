package br.com.Mercatto.service.impl;

import br.com.Mercatto.model.ProfileModel;
import br.com.Mercatto.repositories.ProfileRepository;
import br.com.Mercatto.service.ProfileService;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public List<ProfileModel> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public ProfileModel findById(Long id) {
        return profileRepository.findById(id).orElseThrow(() -> new NoResultException("Perfil não encontrado."));
    }

    @Override
    public ProfileModel save(ProfileModel profileModel) {
        return profileRepository.save(profileModel);
    }

    @Override
    public ProfileModel update(Long id, ProfileModel profileModel) {
        profileRepository.findById(id).orElseThrow(() -> new NoResultException("Perfil não encontrado."));
        profileModel.setProfileId(id);
        profileRepository.save(profileModel);
        return profileModel;
    }

    @Override
    public Long delete(Long id) {
        var response = profileRepository.findById(id).orElseThrow(() -> new NoResultException("Perfil não encontrado."));
        profileRepository.delete(response);
        return id;
    }
}