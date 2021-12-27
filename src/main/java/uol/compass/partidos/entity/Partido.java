package uol.compass.partidos.entity;

import uol.compass.partidos.entity.enums.Ideologia;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Data
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sigla;

    @Enumerated(EnumType.STRING)
    private Ideologia ideologia;

    private LocalTime dataFundacao;
}
