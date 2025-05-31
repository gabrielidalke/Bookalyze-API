package bookalyze.api.demo.contacts.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "contacts")
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contacts_id")
    private Long id;

    @NotBlank(message = "Nome não pode ser vazio.")
    private String name;

    @Email(message = "Email inválido.")
    @NotBlank(message = "Email não pode ser vazio.")
    private String email;

    @Size(max = 20, message = "Telefone não pode ter mais de 20 caracteres.")
    private String phone;

    @NotBlank(message = "Tipo não pode ser vazio.")
    private String type;

    @NotBlank(message = "Documento não pode ser vazio.")
    private String document;
}
