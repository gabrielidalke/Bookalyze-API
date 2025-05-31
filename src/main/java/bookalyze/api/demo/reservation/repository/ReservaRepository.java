package bookalyze.api.demo.reservation.repository;

import bookalyze.api.demo.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reservation, Long> {


    List<Reservation> findByApartment_City(String cidade);


    List<Reservation> findByCheckinDateBetween(LocalDate dataInicio, LocalDate dataFim);


    List<Reservation> findByApartment_CityAndCheckinDateBetween(String cidade, LocalDate dataInicio, LocalDate dataFim);


    @Query("SELECT r FROM Reservation r WHERE r.checkinDate BETWEEN :startDate AND :endDate")
    List<Reservation> findByDataEntradaBetween(LocalDate startDate, LocalDate endDate);


    @Query("SELECT r.channel, SUM(r.totalPrice) FROM Reservation r WHERE r.checkinDate BETWEEN :startDate AND :endDate GROUP BY r.channel")
    List<Object[]> findFaturamentoPorCanalNoUltimoMes(LocalDate startDate, LocalDate endDate);


    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.checkinDate BETWEEN :startDate AND :endDate")
    long countByCheckinDateBetween(LocalDate startDate, LocalDate endDate);
}
