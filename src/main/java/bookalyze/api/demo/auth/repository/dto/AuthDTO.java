package bookalyze.api.demo.auth.repository.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthDTO {
    @NotBlank(message = "Nome de usuário não pode estar nulo")
    private String username;

    @Size(min = 5, message = "Mínimo de 5 caracteres")
    private String password;
}

