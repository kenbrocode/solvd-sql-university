package com.solvd.university.patterns.factory;

import com.solvd.university.dao.UniversityRepository;

public class NoSQLRepositoryFactory implements RepositoryFactory {
    @Override
    public UniversityRepository createUniversityRepository(String type) {
        UniversityRepository result;
        switch (type) {
            case "MONGODB":
                result = new UniversityRepositoryMongoDBImpl();
                break;

            default:
                throw new RuntimeException(String.format("Unable to create  a repository"));
        }
        return result;
    }
}
