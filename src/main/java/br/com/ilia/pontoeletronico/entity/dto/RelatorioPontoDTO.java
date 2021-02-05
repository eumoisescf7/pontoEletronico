package br.com.ilia.pontoeletronico.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelatorioPontoDTO {

    private Long id;
    private String usuario;
    private LocalDate diaTrabalhado;
    private Integer horasExcedentes;
    private Integer horaDevidas;
    private Integer horasTrabalhadas;
    private Long projeto;

}
