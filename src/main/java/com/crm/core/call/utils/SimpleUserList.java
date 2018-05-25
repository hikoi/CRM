package com.crm.core.call.utils;

import java.util.List;

public class SimpleUserList {
    public String errcode;
    public String errmsg;
    public List<Department> department;


    public static class Department {

        public String id;

        public String name;
        public String parentid;
        public String order;
    }

}
