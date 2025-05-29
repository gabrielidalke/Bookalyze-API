package bookalyze.api.demo.reservation.repository;

import bookalyze.api.demo.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reservation, Long> {

    // Buscar reservas por cidade do apartamento
    List<Reservation> findByApartment_City(String cidade);

    // Buscar reservas por intervalo de datas de check-in
    List<Reservation> findByCheckinDateBetween(LocalDate dataInicio, LocalDate dataFim);

    // Buscar reservas por cidade e intervalo de datas de check-in
    List<Reservation> findByApartment_CityAndCheckinDateBetween(String cidade, LocalDate dataInicio, LocalDate dataFim);
}
