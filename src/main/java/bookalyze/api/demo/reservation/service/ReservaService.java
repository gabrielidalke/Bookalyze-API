package bookalyze.api.demo.reservation.service;

import bookalyze.api.demo.reservation.entity.Reservation;
import bookalyze.api.demo.reservation.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<Reservation> listarTodas() {
        return reservaRepository.findAll();
    }

    public Reservation criar(Reservation reserva) {
        return reservaRepository.save(reserva);
    }


}
