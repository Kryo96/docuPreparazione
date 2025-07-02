package org.example;

import jakarta.inject.Inject;
import org.example.dao.EmployeeDao.impl.EmployeeDaoWritableTransactional;
import org.example.qualifier.DaoProfile;

public class EmployeeDaoAuthorizedWritable {

    @Inject
    @DaoProfile(role = DaoProfile.Role.WRITABLE, mode = DaoProfile.Mode.TRANSACTIONAL)
    private EmployeeDaoWritableTransactional writableDao;

//    @Inject
//    private SecurityService securityService;
//
//    public int insertEmployee(Map<String, Object> empData) throws SQLException {
//        if (!securityService.hasRole("ADMIN")) {
//            throw new SecurityException("Accesso negato");
//        }
//        return writableDao.insertEmployee(empData);
//    }
//
//    public int updateSalaryAndBonus(String empNo, double salary, double bonus) throws SQLException {
//        if (!securityService.hasRole("HR")) {
//            throw new SecurityException("Accesso negato");
//        }
//        return writableDao.updateSalaryAndBonus(empNo, salary, bonus);
//    }

    // e così via per gli altri metodi di scrittura
}
