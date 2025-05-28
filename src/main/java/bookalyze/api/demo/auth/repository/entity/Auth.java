package bookalyze.api.demo.auth.repository.entity;

import jakarta.persistence.*;
        import lombok.Data;

@Entity
@Data
@Table(name = "auth")
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auth_id;

    private String password;
    private String username;
    private String salt;
}
