package com.solvd.university.patterns.factory;

public class AbstractRepositoryFactory {
    public static RepositoryFactory createFactory(String type) {
        RepositoryFactory result;
        switch (type) {
            case "RELATIONAL":
                result = new RelationalRepositoriesFactory();
                break;
            case "NOSQL":
                result = new NoSQLRepositoryFactory();
                break;
            default:
                throw new RuntimeException(String.format("Unable to find a needed factory"));
        }
        return result;
    }
}
