package org.sang.service.company;

import org.sang.bean.company.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployee(Integer page, Integer count, String keywords);

    int getEmployeeCount(String keywords);

    List<Employee> getEmployee();

    int addEmployee(Employee employee);

    Employee getEmployeeById(int id);

    int updateEmployee(Employee employee);

    int deleteEmployee(int[] ids);

    List<Employee> getPurEmployee();
}
