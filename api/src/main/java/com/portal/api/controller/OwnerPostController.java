package com.portal.api.controller;

import com.portal.api.dto.OwnerPostDTO;
import com.portal.api.service.OwnerPostService;
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

@RestController
@RequestMapping(value = "/owner", produces = {"application/json"})
@Tag(name = "owner")
public class OwnerPostController {

    @Autowired
    private OwnerPostService ownerPostService;

    private final Logger LOG = LoggerFactory.getLogger(OwnerPostController.class);


    @Operation(summary = "Atualiza os dados do dono do post de carro", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização dos dados do owner realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar os dados de Owner "),
    })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createOwnerCar(@RequestBody OwnerPostDTO ownerPostDTO) {
        LOG.info("USING REST API - Create New Owner  information: {}", ownerPostDTO);
        ownerPostService.createOwnerCar(ownerPostDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
