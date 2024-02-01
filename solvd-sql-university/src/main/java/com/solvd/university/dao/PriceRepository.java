package com.solvd.university.dao;

import com.solvd.university.model.Price;

public interface PriceRepository {
    void create(Price price);

    Price findById(Long priceId);
}
