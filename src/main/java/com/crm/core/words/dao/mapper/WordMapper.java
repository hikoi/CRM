package com.crm.core.words.dao.mapper;

import com.crm.core.words.entity.Word;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface WordMapper {

    void save(Word sensitive);

    void update(Word sensitive);

    Word get(@Param("params") Criteria criteria);

    List<Word> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}
