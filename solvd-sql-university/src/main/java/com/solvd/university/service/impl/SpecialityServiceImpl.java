package com.solvd.university.service.impl;

import com.solvd.university.dao.SpecialityRepository;
import com.solvd.university.dao.impl.myBatis.SpecialityRepositoryMyBatisImpl;
import com.solvd.university.model.Speciality;
import com.solvd.university.service.Service;

public class SpecialityServiceImpl implements Service<Speciality> {
    private SpecialityRepository specialityRepository;

    public SpecialityServiceImpl() {
        this.specialityRepository = new SpecialityRepositoryMyBatisImpl();
    }

    public void create(Speciality speciality) {
        if (speciality.getTitle() != null) {
            speciality.setId(null);
            specialityRepository.create(speciality);
        }
    }

    public void update(Speciality speciality) {
        specialityRepository.update(speciality);
    }
}
