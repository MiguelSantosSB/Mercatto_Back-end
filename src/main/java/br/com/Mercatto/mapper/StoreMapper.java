package br.com.Mercatto.mapper;

import br.com.Mercatto.dto.request.StoreRequest;
import br.com.Mercatto.dto.response.StoreResponse;
import br.com.Mercatto.model.StoreModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class StoreMapper {

    public static StoreModel toModel(StoreRequest request) {
        return StoreModel.builder()
                .name(request.getName())
                .address(request.getAddress())
                .telephone(request.getTelephone())
                .cnpj(request.getCnpj())
                .build();
    }

    public static StoreResponse toResponse(StoreModel model) {
        return StoreResponse.builder()
                .storeId(model.getStoreId())
                .name(model.getName())
                .address(model.getAddress())
                .telephone(model.getTelephone())
                .cnpj(model.getCnpj())
                .build();
    }

    public static List<StoreResponse> storeResponseList(List<StoreModel> storeModelList) {
        if (Objects.isNull(storeModelList)) {
            return new ArrayList<>();
        } else {
            return storeModelList.stream().map(StoreMapper::toResponse).collect(Collectors.toList());
        }
    }
}