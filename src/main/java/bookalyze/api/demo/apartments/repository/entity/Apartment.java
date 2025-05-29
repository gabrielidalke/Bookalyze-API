package bookalyze.api.demo.apartments.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "apartment")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Apartment_id;


    private String title;
    private String city;
    private String state;
    private int max_guests;
    private double daily_rate;

}
