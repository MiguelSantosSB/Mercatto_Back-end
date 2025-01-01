package br.com.Mercatto.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "ProfileResponse", description = "Representa os dados de retorno ao cadastrar um perfil.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponse {

    private Long profileId;

    @NotBlank(message = "{profile.required.name}")
    @JsonProperty("profile")
    @Schema(description = "Nome do Perfil", example = "Administrador")
    private String profile;
}
