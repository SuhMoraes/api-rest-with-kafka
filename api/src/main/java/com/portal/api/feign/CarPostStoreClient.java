package com.portal.api.feign;

import com.portal.api.dto.CarPostDTO;
import com.portal.api.dto.OwnerPostDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @FeignClient(name = "postStoreClient", url = "http://localhost:8082")
    public interface CarPostStoreClient {

        @GetMapping("/sales/cars")
        List<CarPostDTO> carForSaleClient();

        @PostMapping("/user")
        void ownerPostsClient(OwnerPostDTO newUser);

        @PutMapping("/sales/car/{id}")
        void changeCarForSaleClient(@PathVariable("id") String id, @RequestBody CarPostDTO carPostDTO);

        @DeleteMapping("/sales/car/{id}")
        void deleteCarForSaleClient(@PathVariable("id") String id);
    }


