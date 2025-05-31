package bookalyze.api.demo.reservation.service;

import bookalyze.api.demo.reservation.entity.Reservation;
import bookalyze.api.demo.reservation.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;


    public List<Reservation> listarTodas() {
        return reservaRepository.findAll();
    }


    public Object getEstatisticasUltimoMes() {

        LocalDate hoje = LocalDate.now();
        LocalDate fimMes = hoje.minusMonths(1).withDayOfMonth(hoje.minusMonths(1).lengthOfMonth());
        LocalDate inicioMes = fimMes.withDayOfMonth(1);


        long totalReservas = reservaRepository.countByCheckinDateBetween(inicioMes, fimMes);


        List<Object[]> faturamentoPorCanal = reservaRepository.findFaturamentoPorCanalNoUltimoMes(inicioMes, fimMes);


        return new Object[]{totalReservas, faturamentoPorCanal};
    }
}

