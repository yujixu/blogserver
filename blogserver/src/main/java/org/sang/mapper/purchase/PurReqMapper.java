package org.sang.mapper.purchase;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.purchase.PurReq;

import java.util.List;

@Mapper
public interface PurReqMapper {
    int addNewPurReq(PurReq purReq);

    int getPurReqCount(@Param("keywords")String keywords);

    List<PurReq> getPurReq(@Param("page")Integer page, @Param("count")Integer count, @Param("start")Integer start, @Param("keywords")String keywords);

    PurReq getPurReqById(int id);

    int deletePurReqById(@Param("ids")int[] ids);

    int submitPurReq(int id);

    List<PurReq> getExcutePurReq();

    void updatePurReq(PurReq purReq);

    void check(int id);
}
