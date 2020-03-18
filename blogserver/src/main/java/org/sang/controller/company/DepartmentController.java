package org.sang.controller.company;

import org.sang.bean.blog.RespBean;
import org.sang.bean.company.Department;
import org.sang.service.company.DepartmentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dept")
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Map<String, Object> getAllDepartment(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords){
        int totalCount = departmentService.getDepartmentCount(keywords);
        List<Department> departmentList = departmentService.getAllDepartment(page, count, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("dept", departmentList);
        return map;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Department> getDepartment(){
        return departmentService.getDepartment();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addDepartment(Department department){
        int result = departmentService.addDepartment(department);
        if(result==1){
            return new RespBean("success", "保存成功!");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Department getDepartmentById(@PathVariable int id){
        return departmentService.getDepartmentById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public RespBean deleteDepartment(int[] ids){
        int result = departmentService.deleteDepartment(ids);
        if (result == ids.length) {
            return new RespBean("success", "删除成功!");
        }
        if(result == 0){
            return new RespBean("error", "部门被使用,无法删除!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateDepartment(Department department){
        if(departmentService.updateDepartment(department)==1){
            return new RespBean("success", "保存成功");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }
}
