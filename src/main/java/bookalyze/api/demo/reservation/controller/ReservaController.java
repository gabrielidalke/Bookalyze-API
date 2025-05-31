package bookalyze.api.demo.reservation.controller;

import bookalyze.api.demo.reservation.entity.Reservation;
import bookalyze.api.demo.reservation.service.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public List<Reservation> listarTodas() {
        return reservaService.listarTodas();
    }

    @PostMapping
    public ResponseEntity<Reservation> criarReserva(@RequestBody Reservation reserva) {

        return ResponseEntity.status(201).body(reserva);
    }


    @GetMapping("/estatisticas")
    public Object getEstatisticas() {
        return reservaService.getEstatisticasUltimoMes();
    }



}
