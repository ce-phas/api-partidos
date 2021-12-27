package entity;

import entity.enums.Cargo;
import entity.enums.Sexo;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Data
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Cargo cargo = Cargo.NENHUM;

    private LocalTime dataNasc;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @ManyToOne
    @JoinColumn(name = "partido_id")
    private Partido partido;
}
