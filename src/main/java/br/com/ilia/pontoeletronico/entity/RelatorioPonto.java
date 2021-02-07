package br.com.ilia.pontoeletronico.entity;

import br.com.ilia.pontoeletronico.message.Messages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelatorioPonto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String usuario;
    private LocalDate diaTrabalhado;
    private Long horasExcedentes;
    private Long horasDevidas;
    private Long horasTrabalhadas;
    @ManyToOne
    @JoinColumn(name = "ponto_id")
    @NotNull(message = Messages.POINT_VALUE_REQUERID)
    private Ponto ponto;

}
