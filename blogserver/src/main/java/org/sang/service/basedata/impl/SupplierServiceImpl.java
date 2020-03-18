package org.sang.service.basedata.impl;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import org.sang.bean.basedata.Supplier;
import org.sang.mapper.basedata.SupplierMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SupplierServiceImpl {
    @Resource
    private SupplierMapper supplierMapper;

    public int getSupplierCount(String keywords) {
        return supplierMapper.getSupplierCount(keywords);
    }

    public List<Supplier> getAllSupplier(Integer page, Integer count, String keywords) {
        int start = (page - 1) * count;
        return supplierMapper.getAllSupplier(page, count, start, keywords);
    }

    public List<Supplier> getSupplier() {
        return supplierMapper.getSupplier();
    }

    public int addSupplier(Supplier baseUnit) {
        return supplierMapper.addSupplier(baseUnit);
    }

    public Supplier getSupplierById(int id) {
        return supplierMapper.getSupplierById(id);
    }

    public int deleteSupplier(int[] ids) {
        try{
            return supplierMapper.deleteSupplier(ids);
        }catch (DataAccessException e){
            return -1;
        }
    }

    public int updateSupplier(Supplier baseUnit) {
        return supplierMapper.updateSupplier(baseUnit);
    }
}
