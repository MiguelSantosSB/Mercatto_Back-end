package br.com.Mercatto.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

@Data
public class StoreRequest {

    @NotBlank(message = "Informe o nome.")
    private String name;

    @NotBlank(message = "Informe o endereço.")
    private String address;

    @NotBlank(message = "Informe o telefone.")
    private String telephone;

    @CNPJ
    @NotBlank(message = "Informe o cnpj.")
    private String cnpj;

}
