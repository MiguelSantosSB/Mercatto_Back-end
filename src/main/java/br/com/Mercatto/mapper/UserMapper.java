package br.com.Mercatto.mapper;

import br.com.Mercatto.dto.request.UserRequest;
import br.com.Mercatto.dto.response.UserResponse;
import br.com.Mercatto.model.ProfileModel;
import br.com.Mercatto.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public static UserModel insertRequestToModel(UserRequest request) {
        var response = new UserModel();
        BeanUtils.copyProperties(request, response);
        response.setProfileList(UserMapper.toModelList(request.getProfileIds()));

        return response;
    }

    public static UserResponse modelToInsertResponse(UserModel model) {
        var response = new UserResponse();
        BeanUtils.copyProperties(model, response);
        response.setProfileResponseList(ProfileMapper.profileResponseList(model.getProfileList()));

        return response;
    }

    public static UserResponse modelToResponse(UserModel model) {
        var response = new UserResponse();
        BeanUtils.copyProperties(model, response);
        response.setProfileResponseList(ProfileMapper.profileResponseList(model.getProfileList()));

        return response;
    }

    public static List<UserResponse> userResponseList(List<UserModel> perfilList) {
        var perfilResponse = new ArrayList<UserResponse>();
        perfilResponse.addAll(perfilList.stream().map(UserMapper::modelToResponse).collect(Collectors.toList()));
        return perfilResponse;
    }

    //Many to many
    public static List<ProfileModel> toModelList(List<Long> idList) {
        return idList.stream().map(UserMapper::createProfile).collect(Collectors.toList());
    }

    public static ProfileModel createProfile(Long id) {
        return ProfileModel.builder()
                .profileId(id)
                .build();
    }
}