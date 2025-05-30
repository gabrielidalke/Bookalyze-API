package bookalyze.api.demo.reservation.controller;

import bookalyze.api.demo.apartments.repository.ApartmentRepository;
import bookalyze.api.demo.apartments.repository.entity.Apartment;
import bookalyze.api.demo.reservation.entity.Reservation;
import bookalyze.api.demo.reservation.service.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional; // Precisa deste import, se não tiver

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}, allowedHeaders = "*", allowCredentials = "true")
public class ReservaController {

    private final ReservaService reservaService;
    private final ApartmentRepository apartmentRepository;
    // Removendo ContactRepository pois não era injetado na sua versão original

    public ReservaController(ReservaService reservaService, ApartmentRepository apartmentRepository) {
        this.reservaService = reservaService;
        this.apartmentRepository = apartmentRepository;
    }

    @GetMapping
    public List<Reservation> listarTodas() {
        return reservaService.listarTodas();
    }

    @PostMapping
    public ResponseEntity<Reservation> criarReserva(@RequestBody Reservation reserva) {
        // Esta lógica busca apenas o Apartamento. O Contato não é buscado/validado aqui.
        Optional<Apartment> apartmentOpt = Optional.empty();
        if (reserva.getApartment() != null && reserva.getApartment().getId() != null) {
            apartmentOpt = apartmentRepository.findById(reserva.getApartment().getId());
        }

        if (apartmentOpt.isPresent()) {
            reserva.setApartment(apartmentOpt.get());
            Reservation novaReserva = reservaService.criar(reserva);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaReserva);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    // PUT e DELETE não estavam na sua versão original que me forneceu, então não estão aqui.
    // Dashboard e Endpoints de busca filtrada também não estavam.
}