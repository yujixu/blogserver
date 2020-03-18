package org.sang.mapper.company;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.company.Department;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    int getDepartmentCount(@Param("keywords")String keywords);

    List<Department> getAllDepartment(@Param("page")Integer page, @Param("count")Integer count, @Param("start")Integer start, @Param("keywords")String keywords);

    List<Department> getDepartment();

    int addDepartment(Department department);

    Department getDepartmentById(int id);

    int deleteDepartment(@Param("ids")int[] ids);

    int updateDepartment(Department department);
}
