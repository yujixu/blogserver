package org.sang.mapper.company;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.company.Employee;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    List<Employee> getAllEmployee(@Param("page")Integer page, @Param("count")Integer count, @Param("start")Integer start, @Param("keywords")String keywords);

    int getEmployeeCount(@Param("keywords")String keywords);

    List<Employee> getEmployee();

    int addEmployee(Employee employee);

    Employee getEmployeeById(int id);

    int updateEmployee(Employee employee);

    int deleteEmployee(@Param("ids")int[] ids);

    List<Employee> getPurEmployee();
}
