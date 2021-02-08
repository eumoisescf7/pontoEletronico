package br.com.ilia.pontoeletronico.controller;

import br.com.ilia.pontoeletronico.service.RelatorioPontoService;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("relatorio-ponto")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RelatorioPontoController {


    private final RelatorioPontoService service;


    @ApiOperation(value = "Relatório ponto eletrônico")
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Retorna um relatório mensal de todos os pontos realizados"),
            @ApiResponse(
                    code = 500,
                    message = "Ocorreu um erro ao processa a requisição"
            ),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{data}")
    public ResponseEntity<?> relatorioPontos(@NotNull @PathVariable("data") String data){
        try {
            return new ResponseEntity<>(service.gerarRelatorio(data), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
