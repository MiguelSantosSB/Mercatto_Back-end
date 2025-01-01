package br.com.Mercatto.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import java.util.List;

@Data
public class UserRequest {

    @NotBlank(message = "Informe um email válido.")
    @Email
    private String email;

    @NotBlank(message = "Informe a senha.")
    @Size(min = 6, max = 16)
    private String password;

    @NotBlank(message = "Informe um número válido.")
    @NumberFormat
    private String number;

    @NotBlank(message = "Infome o/os IDs dos perfis.")
    private List<Long> profileIds;
}