package bookalyze.api.demo.reserva.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cidade;

    private String canal;

    private Double valor;

    private LocalDate dataEntrada;

    private LocalDate dataSaida;

    private String nomeContato;

    private String telefoneContato;
}
