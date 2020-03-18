package org.sang.service.company.impl;

import org.sang.bean.company.Department;
import org.sang.mapper.company.DepartmentMapper;
import org.sang.service.company.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public int getDepartmentCount(String keywords) {
        return departmentMapper.getDepartmentCount(keywords);
    }

    @Override
    public List<Department> getAllDepartment(Integer page, Integer count, String keywords) {
        int start = (page - 1) * count;
        return departmentMapper.getAllDepartment(page, count, start, keywords);
    }

    @Override
    public List<Department> getDepartment() {
        return departmentMapper.getDepartment();
    }

    @Override
    public int addDepartment(Department department) {
        return departmentMapper.addDepartment(department);
    }

    @Override
    public Department getDepartmentById(int id) {
        return departmentMapper.getDepartmentById(id);
    }

    @Override
    public int deleteDepartment(int[] ids) {
        try{
            return departmentMapper.deleteDepartment(ids);
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public int updateDepartment(Department department) {
        return departmentMapper.updateDepartment(department);
    }
}
