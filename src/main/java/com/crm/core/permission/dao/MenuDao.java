package com.crm.core.permission.dao;

import com.crm.core.permission.dao.mapper.MenuMapper;
import com.crm.core.permission.entity.Menu;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.utils.IDGenerator;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.Date;
import java.util.List;

@Repository
public class MenuDao{

    private Logger logger = LoggerFactory.getLogger(MenuDao.class);

    @Autowired
    private MenuMapper mapper;

    public void saveOrUpdate(Menu menu){
        try{
            Assert.notNull(menu, "菜单信息不能为空");

            if(StringUtils.isBlank(menu.getId())){
                menu.setId(IDGenerator.uuid32());
                menu.setCreateTime(new Date());
                mapper.save(menu);
            }else{
                menu.setUpdateTime(new Date());
                mapper.update(menu);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void saveList(List<Menu> menus){
        try{
            Assert.notEmpty(menus, "菜单信息列表不能为空");

            final Date now = new Date();

            for(Menu menu : menus){
                menu.setId(IDGenerator.uuid32());
                menu.setCreateTime(now);
            }

            mapper.saveList(menus);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Menu getById(String id){
        try{
            Assert.hasText(id, "菜单ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("id", id));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Menu> find(String name, String url, List<String> ids){
        try{
            Criteria criteria = new Criteria();

            if(StringUtils.isNotBlank(name)){
                criteria.and(Restrictions.like("name", name));
            }
            if(StringUtils.isNotBlank(url)){
                criteria.and(Restrictions.like("url", url));
            }
            if(ids != null && !ids.isEmpty()){
                criteria.and(Restrictions.in("id", ids));
            }

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
