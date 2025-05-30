package bookalyze.api.demo.auth.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Auth {
    @Id
    private String username;
    private String password;
}

