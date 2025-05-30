package bookalyze.api.demo.reservation.service;

import bookalyze.api.demo.reservation.entity.Reservation;
import bookalyze.api.demo.reservation.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    // Método para listar todas as reservas
    public List<Reservation> listarTodas() {
        return reservaRepository.findAll();
    }

    // Método para criar uma nova reserva
    public Reservation criar(Reservation reserva) {
        return reservaRepository.save(reserva);
    }


}

