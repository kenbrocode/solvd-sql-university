package com.solvd.university.patterns.factory;

import com.solvd.university.dao.UniversityRepository;
import com.solvd.university.dao.impl.jdbc.UniversityRepositoryImpl;
import com.solvd.university.dao.impl.myBatis.UniversityRepositoryMyBatisImpl;

public class RelationalRepositoriesFactory implements RepositoryFactory {
    @Override
    public UniversityRepository createUniversityRepository(String type) {
        UniversityRepository result;
        switch (type) {
            case "JDBC":
                result = new UniversityRepositoryImpl();
                break;
            case "MYBATIS":
                result = new UniversityRepositoryMyBatisImpl();
                break;
            default:
                throw new RuntimeException(String.format("Unable to create  a repository"));
        }
        return result;
    }
}
