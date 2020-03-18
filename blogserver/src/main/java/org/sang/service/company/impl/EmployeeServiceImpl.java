package org.sang.service.company.impl;

import org.sang.bean.company.Department;
import org.sang.bean.company.Employee;
import org.sang.mapper.company.DepartmentMapper;
import org.sang.mapper.company.EmployeeMapper;
import org.sang.service.company.EmployeeService;
import org.sang.utils.Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;
    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public List<Employee> getAllEmployee(Integer page, Integer count, String keywords) {
        int start = (page - 1) * count;
        return employeeMapper.getAllEmployee(page, count, start, keywords);
    }

    @Override
    public int getEmployeeCount(String keywords) {
        return employeeMapper.getEmployeeCount(keywords);
    }

    @Override
    public List<Employee> getEmployee() {
        return employeeMapper.getEmployee();
    }

    @Override
    public int addEmployee(Employee employee) {
        Department department = departmentMapper.getDepartmentById(employee.getDeptId());
        int count = employeeMapper.getEmployeeCount("")+1;
        employee.setNumber(Util.getPinYinHeadChar(department.getName()) + "-" +count);
        return employeeMapper.addEmployee(employee);
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeMapper.getEmployeeById(id);
    }

    @Override
    public int updateEmployee(Employee employee) {
        System.err.println(employee.getId());
        Department department = departmentMapper.getDepartmentById(employee.getDeptId());
        String num = employee.getNumber().substring(3);
        employee.setNumber(Util.getPinYinHeadChar(department.getName()) + num);
        return employeeMapper.updateEmployee(employee);
    }

    @Override
    public int deleteEmployee(int[] ids) {
        try{
            return employeeMapper.deleteEmployee(ids);
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public List<Employee> getPurEmployee() {
        return employeeMapper.getPurEmployee();
    }
}
