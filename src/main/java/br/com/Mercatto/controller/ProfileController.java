package br.com.Mercatto.controller;

import br.com.Mercatto.controller.interfaces.IProfileController;
import br.com.Mercatto.dto.message.MessageError;
import br.com.Mercatto.dto.request.ProfileRequest;
import br.com.Mercatto.dto.response.ProfileResponse;
import br.com.Mercatto.mapper.ProfileMapper;
import br.com.Mercatto.service.impl.ProfileServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/profile")
public class ProfileController implements IProfileController {

    private final ProfileServiceImpl profileService;

    @Override
    public ResponseEntity<ProfileResponse> save(ProfileRequest request) {
        var response = ProfileMapper.toModel(request);
        return ResponseEntity.ok().body(ProfileMapper.toResponse(profileService.save(response)));
    }

    @Override
    public ResponseEntity<ProfileResponse> update(Long id, ProfileRequest request) {
        var response = ProfileMapper.toModel(request);
        return ResponseEntity.ok().body(ProfileMapper.toResponse(profileService.update(id, response)));
    }

    @Override
    public ResponseEntity<List<ProfileResponse>> findAll() {
        return ResponseEntity.ok().body(ProfileMapper.profileResponseList(profileService.findAll()));
    }

    @Override
    public ResponseEntity<ProfileResponse> findById(Long id) {
        return ResponseEntity.ok().body(ProfileMapper.toResponse(profileService.findById(id)));
    }

    @Override
    public ResponseEntity<MessageError> delete(Long id) {
        profileService.delete(id);
        var messageError = new MessageError();
        messageError.setMessage("Perfil deletado com sucesso");
        messageError.setStatusCode(Integer.valueOf(200));
        return ResponseEntity.ok().body(messageError);
    }
}
