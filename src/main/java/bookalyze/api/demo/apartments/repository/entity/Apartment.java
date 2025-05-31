package bookalyze.api.demo.apartments.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "apartments")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String title;
    private String city;
    private String state;

    @Column(name = "max_guests")
    private int maxGuests;

    @Column(name = "daily_rate")
    private double dailyRate;
}

