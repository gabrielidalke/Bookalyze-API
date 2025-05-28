package bookalyze.api.demo.reserva.repository;

import bookalyze.api.demo.reserva.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByCidadeAndDataEntradaBetween(String cidade, LocalDate inicio, LocalDate fim);

    @Query("SELECT r.canal, COUNT(r), SUM(r.valor) FROM Reserva r WHERE r.dataEntrada >= :data GROUP BY r.canal")
    List<Object[]> totalReservasEFaturamentoPorCanal(LocalDate data);
}
