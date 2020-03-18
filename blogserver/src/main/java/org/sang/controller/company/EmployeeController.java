package org.sang.controller.company;

import org.sang.bean.blog.RespBean;
import org.sang.bean.company.Employee;
import org.sang.service.company.EmployeeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Map<String, Object> getAllEmployee(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords){
        int totalCount = employeeService.getEmployeeCount(keywords);
        List<Employee> employeeList = employeeService.getAllEmployee(page, count, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("employee", employeeList);
        return map;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Employee> getEmployee(){
        return employeeService.getEmployee();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addEmployee(Employee employee){
        int result = employeeService.addEmployee(employee);
        if(result==1){
            return new RespBean("success", "保存成功!");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Employee getEmployeeById(@PathVariable int id){
        return employeeService.getEmployeeById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
    public RespBean deleteEmployee(int[] ids){
        int result = employeeService.deleteEmployee(ids);
        if (result == ids.length) {
            return new RespBean("success", "删除成功!");
        }
        if(result == 0){
            return new RespBean("error", "部门被使用,无法删除!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateEmployee(Employee employee){
        if(employeeService.updateEmployee(employee)==1){
            return new RespBean("success", "保存成功");
        }else{
            return new RespBean("error", "保存失败!");
        }
    }

    @RequestMapping(value = "/pur", method = RequestMethod.GET)
    public List<Employee> getPurEmployee(){
        return employeeService.getPurEmployee();
    }
}
