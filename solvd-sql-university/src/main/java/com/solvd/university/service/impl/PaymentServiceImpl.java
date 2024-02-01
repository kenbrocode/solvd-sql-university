package com.solvd.university.service.impl;

import com.solvd.university.dao.PaymentRepository;
import com.solvd.university.dao.impl.myBatis.PaymentRepositoryMyBatisImpl;
import com.solvd.university.model.Payment;
import com.solvd.university.service.Service;

public class PaymentServiceImpl implements Service<Payment> {
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl() {
        this.paymentRepository = new PaymentRepositoryMyBatisImpl();
    }

    public void create(Payment payment) {
        if(payment.getPriceId()!=null && payment.getStudentId()!=null) {
            payment.setId(null);
            paymentRepository.create(payment);
        }
    }

    public void delete(Payment payment) {
        paymentRepository.delete(payment);
    }
}
