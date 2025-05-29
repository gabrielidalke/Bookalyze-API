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

    // Método para buscar reservas com base na cidade e intervalo de datas
    public List<Reservation> buscarReservas(String cidade, LocalDate dataInicio, LocalDate dataFim) {
        // Se cidade e intervalo de datas forem fornecidos
        if (cidade != null && dataInicio != null && dataFim != null) {
            return reservaRepository.findByApartment_CityAndCheckinDateBetween(cidade, dataInicio, dataFim);
        }
        // Se somente cidade for fornecida
        if (cidade != null) {
            return reservaRepository.findByApartment_City(cidade);
        }
        // Se somente intervalo de datas for fornecido
        if (dataInicio != null && dataFim != null) {
            return reservaRepository.findByCheckinDateBetween(dataInicio, dataFim);
        }
        // Se nenhum filtro for fornecido, retorna todas as reservas
        return reservaRepository.findAll();
    }
}

