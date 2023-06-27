package com.portal.api.service;

import com.portal.api.dto.CarPostDTO;
import com.portal.api.feign.CarPostStoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarPostStoreServiceImpl implements CarPostStoreService{

    @Autowired
    private CarPostStoreClient carPostStoreClient;

    @Override
    public List<CarPostDTO> getCarForSales() {

        return null;
    }

    @Override
    public void changeCarForSale(CarPostDTO carPostDTO, String id) {

    }

    @Override
    public void removeCarForSale(String id) {

    }
}
