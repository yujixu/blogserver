package org.sang.service.area;

import org.sang.bean.area.InArea;
import org.sang.bean.area.qo.InAreaQo;
import org.sang.mapper.area.InAreaMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InAreaServiceImpl {
    @Resource
    private InAreaMapper inAreaMapper;

    public int getInAreaCount(String keywords) {
        return inAreaMapper.getInAreaCount(keywords);
    }

    public List<InAreaQo> getAllInArea(Integer page, Integer count, String keywords) {
        int start = (page - 1) * count;
        return inAreaMapper.getAllInArea(page, count, start, keywords);
    }

    public List<InAreaQo> getInArea() {
        return inAreaMapper.getInArea();
    }

    public int addInArea(InArea inArea) {
        return inAreaMapper.addInArea(inArea);
    }

    public InAreaQo getInAreaById(int id) {
        return inAreaMapper.getInAreaById(id);
    }

    public int deleteInArea(int[] ids) {
        try{
            return inAreaMapper.deleteInArea(ids);
        }catch (DataAccessException e){
            return -1;
        }
    }

    public int updateInArea(InArea inArea) {
        return inAreaMapper.updateInArea(inArea);
    }
}
