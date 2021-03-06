package com.crm.core.organization.webservice;

import com.crm.core.organization.entity.Position;
import com.crm.core.organization.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.security.response.Responsed;

import java.util.List;

@RestController
@RequestMapping(value = "/api/1.0/position")
public class PositionRestController{

    @Autowired
    private PositionService positionService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Position> save(@RequestBody Position position){
        positionService.save(position);

        return new Responsed<Position>("保存成功", position);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Position> update(@RequestBody Position position){
        positionService.update(position);

        return new Responsed<Position>("更新成功", position);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<List<Position>> find(String name, String departmentId){
        List<Position> list = positionService.find(name, departmentId);

        return new Responsed<List<Position>>("查询成功", list);
    }
}
