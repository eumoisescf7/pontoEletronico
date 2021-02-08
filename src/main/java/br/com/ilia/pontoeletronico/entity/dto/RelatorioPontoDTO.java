package br.com.ilia.pontoeletronico.entity.dto;

import lombok.*;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

@Data @Builder @NoArgsConstructor @AllArgsConstructor @Getter @ Setter
public class RelatorioPontoDTO {

    private BigInteger id;
    private Date diaTrabalhado;
    private String cpf;
    private String nome;
    private BigInteger horasDevidas;
    private BigInteger horasExcedentes;
    private BigInteger horasTrabalhadas;
    private BigInteger pontoId;
    private BigInteger horasAtribuidas;
    private BigInteger relatorioPontoId;
    private Timestamp entrada1;
    private Timestamp saida1;
    private Timestamp entrada2;
    private Timestamp saida2;
    private String nomeProjeto;

}
