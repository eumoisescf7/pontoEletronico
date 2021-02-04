package br.com.ilia.pontoeletronico.controller;

import br.com.ilia.pontoeletronico.service.PontoService;
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
@RequestMapping("cadastrar-ponto")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PontoController {

    private final PontoService service;


    @ApiOperation(value = "Bater ponto eletrônico")
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Retorna o ponto realizado"),
            @ApiResponse(
                    code = 500,
                    message = "Ocorreu um erro ao processa a requisição"
            ),
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{cpf}")
    public ResponseEntity<?> enviarPonto(@NotNull @PathVariable("cpf") String cpf) throws Exception {

        try {
            return new ResponseEntity<>(service.cadastrarPonto(cpf), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
