package uol.compass.partidos.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import uol.compass.partidos.entity.enums.Ideologia;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFundacao;

    @OneToMany(mappedBy = "partido")
    private List<Associado> associados;
}
