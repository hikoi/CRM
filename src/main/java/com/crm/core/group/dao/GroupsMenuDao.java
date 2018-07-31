package com.crm.core.group.dao;

import com.crm.core.group.consts.GroupType;
import com.crm.core.group.dao.mapper.GroupsMenuMapper;
import com.crm.core.group.entity.GroupsMenu;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.List;

@Repository
public class GroupsMenuDao{

    private Logger logger = LoggerFactory.getLogger(GroupsMenuDao.class);

    @Autowired
    private GroupsMenuMapper mapper;

    public void save(String groupsId, String menuId){
        try{
            Assert.hasText(groupsId, "分组ID不能为空");
            Assert.hasText(menuId, "菜单ID不能为空");

            mapper.save(groupsId, menuId);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void saveList(String groupsId, List<String> menuIds){
        try{
            Assert.hasText(groupsId, "分组ID不能为空");
            Assert.notEmpty(menuIds, "菜单ID列表不能为空");

            mapper.saveList(groupsId, menuIds);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void deleteByGroupsId(String groupsId){
        try{
            Assert.hasText(groupsId, "分组ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("groupsId", groupsId));

            mapper.delete(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void deleteByMenuId(String menuId){
        try{
            Assert.hasText(menuId, "菜单ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("menuId", menuId));

            mapper.delete(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<GroupsMenu> find(String groupsId, String menuId){
        try{
            Criteria criteria = new Criteria();

            criteria.and(Restrictions.eq("g.type", GroupType.MENU.getId()));

            if(StringUtils.isNotBlank(groupsId)){
                criteria.and(Restrictions.eq("gm.groupsId", groupsId));
            }
            if(StringUtils.isNotBlank(menuId)){
                criteria.and(Restrictions.eq("gm.menuId", menuId));
            }

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
