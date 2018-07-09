package com.crm.core.account.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.entity.Account;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface AccountMapper{

    void save(Account account);

    void update(Account account);

    Account get(@Param("params") Criteria criteria);

    List<Account> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}
