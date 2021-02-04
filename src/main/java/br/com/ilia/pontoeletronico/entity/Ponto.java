package br.com.ilia.pontoeletronico.entity;

import br.com.ilia.pontoeletronico.message.Messages;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ponto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String usuario;
    @NotNull(message = Messages.POINT_VALUE_REQUERID)
    private String cpf;
    @NotNull(message = Messages.POINT_VALUE_REQUERID)
    private LocalDate dia;
    private LocalDateTime entrada1;
    private LocalDateTime saida1;
    private LocalDateTime entrada2;
    private LocalDateTime saida2;

}
