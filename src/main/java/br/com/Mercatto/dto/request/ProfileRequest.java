package br.com.Mercatto.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProfileRequest {

    @NotBlank(message = "Informe o nome do perfil")
    private String profile;
}
