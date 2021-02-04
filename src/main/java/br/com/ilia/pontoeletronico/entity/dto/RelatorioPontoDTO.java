package br.com.ilia.pontoeletronico.entity.dto;

import java.time.LocalDate;

public class RelatorioPontoDTO {

    private Long id;
    private String usuario;
    private LocalDate diaTrabalhado;
    private Integer horasExcedentes;
    private Integer horaDevidas;
    private Integer horasTrabalhadas;
    private Long projeto;

}
