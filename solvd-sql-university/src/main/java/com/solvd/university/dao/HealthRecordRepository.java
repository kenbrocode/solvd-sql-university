package com.solvd.university.dao;

import com.solvd.university.model.HealthRecord;

public interface HealthRecordRepository {
    void create(HealthRecord healthRecord);

    void update(HealthRecord healthRecord);

    HealthRecord findById(Long id);
}
