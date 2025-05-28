package bookalyze.api.demo.reserva.service;

import bookalyze.api.demo.reserva.entity.Reserva;
import bookalyze.api.demo.reserva.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<Reserva> listarTodas() {
        return reservaRepository.findAll();
    }

    public Reserva criar(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public List<Reserva> buscarPorCidadeEIntervalo(String cidade, LocalDate inicio, LocalDate fim) {
        return reservaRepository.findByCidadeAndDataEntradaBetween(cidade, inicio, fim);
    }

    public List<Object[]> totalEReceitaPorCanalUltimoMes() {
        return reservaRepository.totalReservasEFaturamentoPorCanal(LocalDate.now().minusMonths(1));
    }
}
