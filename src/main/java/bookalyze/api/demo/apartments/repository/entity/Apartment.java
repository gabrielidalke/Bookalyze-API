package bookalyze.api.demo.apartments.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "apartments") // Corrigido para bater com o nome da tabela no banco
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Nome da coluna no banco
    private Long id;

    private String title;
    private String city;
    private String state;

    @Column(name = "max_guests")
    private int maxGuests;

    @Column(name = "daily_rate")
    private double dailyRate;
}

