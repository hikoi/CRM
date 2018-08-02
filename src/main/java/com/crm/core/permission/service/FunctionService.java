package com.crm.core.permission.service;

import com.crm.core.permission.entity.Function;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

import java.util.List;

public interface FunctionService{

    void save(Function function);

    void update(Function function);

    void saveList(List<Function> functions);

    Function getById(String id);

    List<Function> find(String url, String description, Boolean needAllot, RequestMethod method);
}