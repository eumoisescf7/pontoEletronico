package br.com.ilia.pontoeletronico.controller;

import br.com.ilia.pontoeletronico.service.ProjetoPontoService;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("add-ponto-projeto")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ProjetoPontoController {

    private final ProjetoPontoService service;


    @ApiOperation(value = "Agregar horas trabalho para um projeto específico")
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Retorna uma entidade criada para hora/projeto"),
            @ApiResponse(
                    code = 500,
                    message = "Ocorreu um erro ao processa a requisição"
            ),
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{data}/{cpf}/{projeto}/{qntdHora}")
    public ResponseEntity<?> agregarProjeto(@NotNull @PathVariable("data") String data,
                                            @PathVariable("cpf") String cpf,
                                            @PathVariable("projeto") String projeto,
                                            @PathVariable("qntdHora") String qntdHora) throws Exception {
        try {
            return new ResponseEntity<>(service.agregarDiaProjeto(data, cpf, projeto, qntdHora), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
