package br.com.Mercatto.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreResponse {

    private Long storeId;

    private String name;

    private String address;

    private String telephone;

    private String cnpj;
}
