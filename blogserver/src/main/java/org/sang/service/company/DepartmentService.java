package org.sang.service.company;

import org.sang.bean.company.Department;

import java.util.List;

public interface DepartmentService {
    int getDepartmentCount(String keywords);

    List<Department> getAllDepartment(Integer page, Integer count, String keywords);

    List<Department> getDepartment();

    int addDepartment(Department department);

    Department getDepartmentById(int id);

    int deleteDepartment(int[] ids);

    int updateDepartment(Department department);
}
