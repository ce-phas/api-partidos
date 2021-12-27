package uol.compass.partidos.entity;

import lombok.Data;
import uol.compass.partidos.entity.enums.Ideologia;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    private LocalDate dataFundacao;

    @OneToMany(mappedBy = "partido")
    private Set<Associado> associados;
}
