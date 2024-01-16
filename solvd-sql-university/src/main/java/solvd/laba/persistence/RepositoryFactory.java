package solvd.laba.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import solvd.laba.persistence.impl.DepartmentDAO;
import solvd.laba.persistence.impl.SpecialtyDAO;
import solvd.laba.persistence.impl.SubjectDAO;
import solvd.laba.persistence.impl.mybatis.DepartmentMyBatisImpl;
import solvd.laba.persistence.impl.mybatis.SpecialtyMyBatisImpl;
import solvd.laba.persistence.impl.mybatis.SubjectMyBatisImpl;

import java.lang.invoke.MethodHandles;

public class RepositoryFactory {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static IDepartmentDAO createDepartmentRepository(String type) {
        IDepartmentDAO result;
        switch (type) {
            case "jdbc":
                result = new DepartmentDAO();
                break;
            case "mybatis":
                result = new DepartmentMyBatisImpl();
                break;
            default:
                result = new DepartmentDAO();
                LOGGER.info("Data source was not specified or is invalid. Defaulting to JDBC implementation");
                break;
        }
        return result;
    }

    public static ISpecialtyDAO createSpecialtyRepository(String type) {
        ISpecialtyDAO result;
        switch (type) {
            case "jdbc":
                result = new SpecialtyDAO();
                break;
            case "mybatis":
                result = new SpecialtyMyBatisImpl();
                break;
            default:
                result = new SpecialtyDAO();
                LOGGER.info("Data source was not specified or is invalid. Defaulting to JDBC implementation");
                break;
        }
        return result;
    }

    public static ISubjectDAO createSubjectRepository(String type) {
        ISubjectDAO result;
        switch (type) {
            case "jdbc":
                result = new SubjectDAO();
                break;
            case "mybatis":
                result = new SubjectMyBatisImpl();
                break;
            default:
                result = new SubjectDAO();
                LOGGER.info("Data source was not specified or is invalid. Defaulting to JDBC implementation");
                break;
        }
        return result;
    }
}