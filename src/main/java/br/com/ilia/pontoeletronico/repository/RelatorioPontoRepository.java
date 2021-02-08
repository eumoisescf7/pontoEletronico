package br.com.ilia.pontoeletronico.repository;

import br.com.ilia.pontoeletronico.entity.RelatorioPonto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RelatorioPontoRepository extends JpaRepository<RelatorioPonto, Long> {

    List<RelatorioPonto> findAll();

    Optional<RelatorioPonto> findByDiaTrabalhadoAndUsuario(LocalDate data, String cpf);


    @Query(value = "select pp.id, rp.dia_trabalhado, rp.usuario as cpf, u.nome, rp.horas_devidas, rp.horas_excedentes, rp.horas_trabalhadas, " +
            "   rp.ponto_id, pp.horas_atribuidas, pp.relatorio_ponto_id, po.entrada1, po.saida1, po.entrada2, po.saida2, p.descricao as nome_projeto " +
            "   from relatorio_ponto rp " +
            "   left join projeto_ponto pp on  pp.relatorio_ponto_id = rp.id " +
            "   left join projeto p on p.id = pp.projeto_id " +
            "   left join ponto po on po.id = rp.ponto_id " +
            "   left join usuario u on u.cpf = po.cpf " +
            "   where rp.dia_trabalhado between :dataInicial and  :dataFinal",
            nativeQuery = true)
    List<Tuple> getCurrentRevenue(@Param("dataInicial") LocalDate dataInicial, @Param("dataFinal") LocalDate dataFinal);

}
