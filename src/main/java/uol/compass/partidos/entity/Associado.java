package uol.compass.partidos.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNasc;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @ManyToOne
    @JoinColumn(name = "id_partido")
    private Partido partido;
}
