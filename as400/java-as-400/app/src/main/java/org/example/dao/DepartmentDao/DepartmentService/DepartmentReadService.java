package org.example.dao.DepartmentDao.DepartmentService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import org.example.dao.DepartmentDao.DepartmentDAO;
import org.example.dao.DepartmentDao.DepartmentReadOperations;
import org.example.qualifier.ReadOnly;

import java.util.List;
import java.util.Map;

@ReadOnly
@ApplicationScoped
public class DepartmentReadService implements DepartmentReadOperations {

    @Inject
    private DepartmentDAO dao;

    @Override
    public List<String> getAllDeptNumbers() {
        return dao.getAllDeptNumbers();
    }

    @Override
    public List<String> getAllDeptNames() {
        return dao.getAllDeptNames();
    }

    @Override
    public List<String> getAllLocations() {
        return dao.getAllLocations();
    }

    @Override
    public List<Map<String, Object>> findByDeptNo(String deptNo) {
        return dao.findByDeptNo(deptNo);
    }

    @Override
    public List<Map<String, Object>> findByDeptName(String deptName) {
        return dao.findByDeptName(deptName);
    }

    @Override
    public List<Map<String, Object>> findByManager(String mgrNo) {
        return dao.findByManager(mgrNo);
    }

    @Override
    public List<Map<String, Object>> findByLocation(String location) {
        return dao.findByLocation(location);
    }

    @Override
    public List<Map<String, Object>> findByDeptNameLike(String pattern) {
        return dao.findByDeptNameLike(pattern);
    }

    @Override
    public List<Map<String, Object>> findByLocationLike(String pattern) {
        return dao.findByLocationLike(pattern);
    }

    @Override
    public int countAllDepartments() {
        return dao.countAllDepartments();
    }

    @Override
    public Map<String, Integer> countByLocation() {
        return dao.countByLocation();
    }

    @Override
    public Map<String, Integer> countByManager() {
        return dao.countByManager();
    }

    @Override
    public List<String> getDistinctLocations() {
        return dao.getDistinctLocations();
    }

    @Override
    public List<String> getDistinctManagers() {
        return dao.getDistinctManagers();
    }

    @Override
    public List<Map<String, Object>> findAllOrderByDeptNo() {
        return dao.findAllOrderByDeptNo();
    }

    @Override
    public List<Map<String, Object>> findAllOrderByDeptName() {
        return dao.findAllOrderByDeptName();
    }

    @Override
    public List<Map<String, Object>> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Map<String, Object>> executeCustomSelect(String query) {
        return dao.executeCustomSelect(query);
    }
}
