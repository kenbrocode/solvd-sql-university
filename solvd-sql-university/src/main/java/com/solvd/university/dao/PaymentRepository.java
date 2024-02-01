package com.solvd.university.dao;

import com.solvd.university.model.Payment;

public interface PaymentRepository {
    void create(Payment payment);

    void delete(Payment payment);
}
