package org.example.dao.DepartmentDao;

import java.util.List;
import java.util.Map;

public interface DepartmentReadOperations {

    // SELECT singole colonne
    List<String> getAllDeptNumbers();
    List<String> getAllDeptNames();
    List<String> getAllLocations();


    // SELECT con WHERE
    List<Map<String, Object>> findByDeptNo(String deptNo);
    List<Map<String, Object>> findByDeptName(String deptName);
    List<Map<String, Object>> findByManager(String mgrNo);
    List<Map<String, Object>> findByLocation(String location);


    // SELECT con LIKE
    List<Map<String, Object>> findByDeptNameLike(String pattern);
    List<Map<String, Object>> findByLocationLike(String pattern);


    // SELECT con aggregazioni
    int countAllDepartments();
    Map<String, Integer> countByLocation();
    Map<String, Integer> countByManager();

    // SELECT DISTINCT
    List<String> getDistinctLocations();
    List<String> getDistinctManagers();

    // SELECT con ORDER BY
    List<Map<String, Object>> findAllOrderByDeptNo();
    List<Map<String, Object>> findAllOrderByDeptName();

    // SELECT generico
    List<Map<String, Object>> findAll();
    List<Map<String, Object>> executeCustomSelect(String query);

}
