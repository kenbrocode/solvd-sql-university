package com.solvd.university.service.impl;

import com.solvd.university.dao.CafedraRepository;
import com.solvd.university.dao.impl.myBatis.CafedraRepositoryMyBatisImpl;
import com.solvd.university.model.Cafedra;
import com.solvd.university.service.Service;

public class CafedraServiceImpl implements Service<Cafedra> {

    private final CafedraRepository cafedraRepository;

    public CafedraServiceImpl() {
        this.cafedraRepository = new CafedraRepositoryMyBatisImpl();
    }

    public void create(Cafedra cafedra) {
        if (cafedra.getTitle() != null) {
            cafedra.setId(null);
            cafedraRepository.create(cafedra);
        }
    }

    public void delete(Cafedra cafedra) {
        cafedraRepository.delete(cafedra);
    }

}
