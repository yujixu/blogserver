package org.sang.mapper.purchase;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.bean.purchase.PurContract;
import org.sang.bean.purchase.qo.PurContractQo;

import java.util.List;

@Mapper
public interface PurContractMapper {
    int addNewContract(PurContract purContract);

    int getContractCount(@Param("keywords")String keywords);

    List<PurContract> getContract(@Param("page")Integer page, @Param("count")Integer count, @Param("start")Integer start, @Param("keywords")String keywords);

    PurContractQo getContractById(int id);

    int deleteContractById(@Param("ids")int[] ids);

    int submitContract(int id);

    void updatePurContract(PurContract purContract);

    void check(int id);

    void recheck(int id);

    List<PurContract> getClosePurContract();

    void receipt(int contractId);
}
