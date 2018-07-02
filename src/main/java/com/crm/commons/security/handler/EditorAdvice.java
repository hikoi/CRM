package com.crm.commons.security.handler;

import com.crm.commons.utils.editor.SexEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.wah.doraemon.entity.consts.Sex;

@ControllerAdvice
public class EditorAdvice{

    @InitBinder
    public void initSexBinder(WebDataBinder binder){
        binder.registerCustomEditor(Sex.class, new SexEditor());
    }
}
