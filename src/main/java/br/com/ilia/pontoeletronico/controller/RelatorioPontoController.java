package br.com.ilia.pontoeletronico.controller;

import br.com.ilia.pontoeletronico.entity.RelatorioPonto;
import br.com.ilia.pontoeletronico.service.RelatorioPontoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("relatorio-ponto")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RelatorioPontoController {


    private final RelatorioPontoService service;


    @ApiOperation(value = "Relatorio ponto eletrônico")
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Retorna um relatório de todo o ponto realizado"),
            @ApiResponse(
                    code = 500,
                    message = "Ocorreu um erro ao processa a requisição"
            ),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<RelatorioPonto> relatorioPontos(){
        return service.gerarRelatorio();
    }
}
