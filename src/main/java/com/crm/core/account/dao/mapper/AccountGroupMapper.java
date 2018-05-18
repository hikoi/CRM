package com.crm.core.account.dao.mapper;

import com.crm.core.account.entity.AccountGroup;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface AccountGroupMapper{

    void save(AccountGroup group);

    void update(AccountGroup group);

    AccountGroup get(@Param("params") Criteria criteria);

    List<AccountGroup> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}
