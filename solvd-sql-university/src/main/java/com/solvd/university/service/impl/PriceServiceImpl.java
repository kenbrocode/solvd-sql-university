package com.solvd.university.service.impl;

import com.solvd.university.dao.PriceRepository;
import com.solvd.university.dao.impl.myBatis.PriceRepositoryMyBatisImpl;
import com.solvd.university.model.Price;
import com.solvd.university.service.Service;

public class PriceServiceImpl implements Service<Price> {
    private final PriceRepository priceRepository;

    public PriceServiceImpl() {
        this.priceRepository = new PriceRepositoryMyBatisImpl();
    }

    public void create(Price price) {
        if(price.getCost()!= 0 ) {
            price.setId(null);
            priceRepository.create(price);
        }
    }

    public Price findById(Long priceId) {
        return priceRepository.findById(priceId);
    }
}
