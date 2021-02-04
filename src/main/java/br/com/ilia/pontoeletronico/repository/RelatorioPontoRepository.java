package br.com.ilia.pontoeletronico.repository;

import br.com.ilia.pontoeletronico.model.RelatorioPonto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelatorioPontoRepository extends CrudRepository<RelatorioPonto, Long> {
    List<RelatorioPonto> findAll();
}
