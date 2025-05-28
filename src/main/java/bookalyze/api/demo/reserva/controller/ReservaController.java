package bookalyze.api.demo.reserva.controller;

import bookalyze.api.demo.reserva.entity.Reserva;
import bookalyze.api.demo.reserva.service.ReservaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public List<Reserva> listarTodas() {
        return reservaService.listarTodas();
    }

    @PostMapping
    public Reserva criar(@RequestBody Reserva reserva) {
        return reservaService.criar(reserva);
    }

    @GetMapping("/buscar")
    public List<Reserva> buscarPorCidadeEIntervalo(
            @RequestParam String cidade,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim
    ) {
        return reservaService.buscarPorCidadeEIntervalo(cidade, inicio, fim);
    }

    @GetMapping("/relatorio")
    public List<Object[]> totalEReceitaPorCanalUltimoMes() {
        return reservaService.totalEReceitaPorCanalUltimoMes();
    }
}



