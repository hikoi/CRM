package com.crm.core.group.dao.mapper;

import com.crm.core.group.entity.GroupsSeller;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface GroupsSellerMapper{

    void save(@Param("groupsId") String groupsId, @Param("sellerId") String sellerId);

    void saveList(@Param("groupsId") String groupsId, @Param("sellerIds") List<String> sellerIds);

    void delete(@Param("params") Criteria criteria);

    GroupsSeller get(@Param("params") Criteria criteria);

    List<GroupsSeller> find(@Param("params") Criteria criteria);
}
