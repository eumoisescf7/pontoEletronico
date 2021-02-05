package br.com.ilia.pontoeletronico.repository;

import br.com.ilia.pontoeletronico.entity.RelatorioPonto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RelatorioPontoRepository extends JpaRepository<RelatorioPonto, Long> {

    List<RelatorioPonto> findAll();

    Optional<RelatorioPonto> findByDiaTrabalhadoAndUsuario(LocalDate data, String cpf);

    //@Query(value = "select * from relatorio_ponto rp " +
    //      " left join ponto p on p.id = rp.ponto_id " +
    //       " where  rp.dia_trabalhado >= :dataInicial and rp.dia_trabalhado <= :dataInicial", nativeQuery = true)
    //List<Tuple> getRelatorio(@Param("dataInicial") LocalDate dataInicial, @Param("dataFinal") LocalDate dataFinal);
}
