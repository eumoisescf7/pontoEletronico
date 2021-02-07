package br.com.ilia.pontoeletronico.entity;


import br.com.ilia.pontoeletronico.message.Messages;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @NotNull(message = Messages.POINT_VALUE_REQUERID)
    private String cpf;
}
