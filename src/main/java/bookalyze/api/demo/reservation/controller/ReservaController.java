package bookalyze.api.demo.reservation.controller;

import bookalyze.api.demo.reservation.entity.Reservation;
import bookalyze.api.demo.reservation.service.ReservaService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    // GET: Listar todas as reservas
    @GetMapping
    public List<Reservation> listarTodas() {
        return reservaService.listarTodas();
    }

    // GET: Buscar reservas com base em cidade e datas
    @GetMapping("/buscar")
    public List<Reservation> buscarReservas(
            @RequestParam(required = false) String cidade,
            @RequestParam(required = false) String dataInicio,
            @RequestParam(required = false) String dataFim
    ) {
        LocalDate inicio = null;
        LocalDate fim = null;

        if (dataInicio != null && !dataInicio.isEmpty()) {
            inicio = LocalDate.parse(dataInicio);
        }
        if (dataFim != null && !dataFim.isEmpty()) {
            fim = LocalDate.parse(dataFim);
        }

        return reservaService.buscarReservas(cidade, inicio, fim);
    }

    // POST: Criar nova reserva
    @PostMapping
    public Reservation criarReserva(@RequestBody Reservation reserva) {
        return reservaService.criar(reserva);
    }
}
