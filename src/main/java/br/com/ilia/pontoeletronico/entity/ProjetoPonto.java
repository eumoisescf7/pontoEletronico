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
public class ProjetoPonto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dia;
    @ManyToOne
    @NotNull(message = Messages.POINT_VALUE_REQUERID)
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;
    @ManyToOne
    @JoinColumn(name = "relatorio_ponto_id")
    @NotNull(message = Messages.POINT_VALUE_REQUERID)
    private RelatorioPonto relatorioPonto;
    private Long horasAtribuidas;
}
