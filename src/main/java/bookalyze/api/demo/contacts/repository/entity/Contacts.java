package bookalyze.api.demo.contacts.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "contacts")
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Contacts_id;

    private String name;
    private String email;
    private String phone;
    private String type;
    private String document;

}

