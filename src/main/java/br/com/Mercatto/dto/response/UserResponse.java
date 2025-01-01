package br.com.Mercatto.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(name = "UserResponse", description = "Respresenta os dados de retorno ao cadastrar um usuário.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long userId;

    @NotBlank(message = "{user.required.email}")
    @JsonProperty("email")
    @Schema(description = "Email de acesso")
    private String email;

    @NotBlank(message = "{user.required.password}")
    @JsonProperty("password")
    @Schema(description = "Senha de acesso")
    private String password;

    @NotBlank(message = "{user.required.number}")
    @JsonProperty("number")
    @Schema(description = "Número do usuário")
    private String number;

    private List<ProfileResponse> profileResponseList;
}