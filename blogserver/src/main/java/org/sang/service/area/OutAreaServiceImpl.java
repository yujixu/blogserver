package org.sang.service.area;

import org.sang.bean.area.OutArea;
import org.sang.bean.area.qo.OutAreaQo;
import org.sang.mapper.area.OutAreaMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OutAreaServiceImpl {
    @Resource
    private OutAreaMapper outAreaMapper;

    public int getOutAreaCount(String keywords) {
        return outAreaMapper.getOutAreaCount(keywords);
    }

    public List<OutAreaQo> getAllOutArea(Integer page, Integer count, String keywords) {
        int start = (page - 1) * count;
        return outAreaMapper.getAllOutArea(page, count, start, keywords);
    }

    public List<OutAreaQo> getOutArea() {
        return outAreaMapper.getOutArea();
    }

    public int addOutArea(OutArea outArea) {
        return outAreaMapper.addOutArea(outArea);
    }

    public OutAreaQo getOutAreaById(int id) {
        return outAreaMapper.getOutAreaById(id);
    }

    public int deleteOutArea(int[] ids) {
        try{
            return outAreaMapper.deleteOutArea(ids);
        }catch (DataAccessException e){
            return -1;
        }
    }

    public int updateOutArea(OutArea OutArea) {
        return outAreaMapper.updateOutArea(OutArea);
    }
}
