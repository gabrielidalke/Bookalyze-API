package bookalyze.api.demo.reservation.controller;

import bookalyze.api.demo.reservation.entity.Reservation;
import bookalyze.api.demo.reservation.service.ReservaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
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
    public Reservation criar(@RequestBody Reservation reserva) {
        return reservaService.criar(reserva);
    }


}



