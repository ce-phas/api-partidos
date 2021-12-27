package uol.compass.partidos.entity;

import lombok.Data;
import uol.compass.partidos.entity.enums.Cargo;
import uol.compass.partidos.entity.enums.Sexo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Cargo cargo = Cargo.NENHUM;

    private LocalDate dataNasc;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @ManyToOne
    @JoinColumn(name = "partido_id")
    private Partido partido;
}
