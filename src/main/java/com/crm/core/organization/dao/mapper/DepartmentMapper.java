package com.crm.core.organization.dao.mapper;

import com.crm.core.organization.entity.Department;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface DepartmentMapper{

    void save(Department department);

    void update(Department department);

    Department get(@Param("params") Criteria criteria);

    List<Department> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}
