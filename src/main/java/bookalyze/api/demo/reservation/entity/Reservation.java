package bookalyze.api.demo.reservation.entity;

import bookalyze.api.demo.apartments.repository.entity.Apartment;
import bookalyze.api.demo.contacts.repository.entity.Contacts;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "apartment_id", nullable = false)
    private Apartment apartment;

    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false)
    private Contacts contact;

    @Column(name = "checkin_date", nullable = false)
    private LocalDate checkinDate;

    @Column(name = "checkout_date", nullable = false)
    private LocalDate checkoutDate;

    private int guests;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @Column(length = 20, nullable = false)
    private String channel; // airbnb, booking.com, direto
}
