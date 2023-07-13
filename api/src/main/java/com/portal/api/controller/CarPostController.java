package com.portal.api.controller;

import com.portal.api.dto.CarPostDTO;
import com.portal.api.message.KafkaProducerMessage;
import com.portal.api.service.CarPostStoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping(value = "/api/car", produces = {"application/json"})
@Tag(name = "api-car")
public class CarPostController {

    @Autowired
    private CarPostStoreService carPostStoreService;

    @Autowired
    private KafkaProducerMessage kafkaProducerMessage;
    private final Logger LOG = LoggerFactory.getLogger(CarPostController.class);

    @Operation(summary = "Cria os dados de post de carro", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Criação de post realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao criar post"),
    })
    @PostMapping(name="/posts",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postCarForSale(@RequestBody CarPostDTO carPostDTO){
        LOG.info("USING EVENTS/MESSAGE KAFKA - Producer Car Post information: {}", carPostDTO);
        kafkaProducerMessage.sendMessage(carPostDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Operation(summary = "Pega todos os posts de carro", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca de todos os posts realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar todos os posts"),
    })
    @GetMapping(name="/posts",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CarPostDTO>> getCarSales() {
        return ResponseEntity.status(HttpStatus.FOUND).body(carPostStoreService.getCarForSales());
    }


    @Operation(summary = "Atualiza o post de carro", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização de post realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a atualização de post"),
    })
    @PutMapping(name="/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity changeCarSale(@RequestBody CarPostDTO carPostDTO, @PathVariable("id") String id) {
        carPostStoreService.changeCarForSale(carPostDTO, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Deleta os posts de carro", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete de post realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar post"),
    })
    @DeleteMapping(name="/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteCarForSale(@PathVariable("id") String id) {
        carPostStoreService.removeCarForSale(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
