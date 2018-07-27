package com.crm.core.group.dao.mapper;

import com.crm.core.words.entity.Word;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface GroupsWordMapper{

    void save(@Param("groupsId") String groupsId, @Param("wordId") String wordId);

    void saveList(@Param("groupsId") String groupsId, @Param("wordIds") List<String> wordIds);

    void delete(@Param("params") Criteria criteria);

    List<Word> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}
