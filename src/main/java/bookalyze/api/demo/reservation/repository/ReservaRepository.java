package bookalyze.api.demo.reservation.repository;

import bookalyze.api.demo.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reservation, Long> {

}
