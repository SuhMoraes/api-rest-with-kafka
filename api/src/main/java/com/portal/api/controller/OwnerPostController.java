package com.portal.api.controller;

import com.portal.api.dto.OwnerPostDTO;

import com.portal.api.service.OwnerPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/owner")
public class OwnerPostController {

    @Autowired
    private OwnerPostService ownerPostService;

    @PutMapping
    public ResponseEntity createOwnerCar(@RequestBody OwnerPostDTO ownerPostDTO) {
        ownerPostService.createOwnerCar(ownerPostDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
