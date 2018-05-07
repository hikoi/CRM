package com.crm.commons.utils;

import lombok.NoArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.wah.doraemon.domain.consts.EnumType;
import org.wah.doraemon.utils.NameUtils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

@NoArgsConstructor
public class ExcelUtils{

    public void read(){
        XSSFWorkbook book = new XSSFWorkbook();
    }

    public static <T> XSSFWorkbook write(Map<String, String> map, List<T> contents) throws Exception{
        if(map == null || map.isEmpty()){
            throw new IllegalArgumentException("Excel标题不能为空");
        }

        //创建excel
        XSSFWorkbook book = new XSSFWorkbook();
        //创建工作簿
        XSSFSheet sheet = book.createSheet();
        //标题
        List<String> titles = new ArrayList<String>();
        Iterator<String> iterator = map.keySet().iterator();
        while(iterator.hasNext()){
            titles.add(iterator.next());
        }

        //创建标题
        XSSFRow titleRow = sheet.createRow(0);
        for(int i = 0; i < titles.size(); i++){
            XSSFCell cell = titleRow.createCell(i);
            cell.setCellValue(titles.get(i));
        }

        //填充正文
        for(int i = 1; i <= contents.size(); i++){
            //创建行
            XSSFRow row = sheet.createRow(i);
            //行内容对象
            T entry = contents.get(i - 1);
            Class clazz = entry.getClass();

            for(int j = 0; j < titles.size(); j++){
                //反射GET方法
                Method getter = clazz.getMethod("get" + NameUtils.upperCaseToFirst(map.get(titles.get(j))));
                getter.setAccessible(true);
                Object value = getter.invoke(entry, (Object[]) null);
                //赋值
                XSSFCell cell = row.createCell(j);
                setValueAsString(value, cell);
            }
        }

        return book;
    }

    public static void setValueAsString(Object value, XSSFCell cell) throws Exception{
        if(value == null){
            cell.setCellValue("");
            cell.setCellType(XSSFCell.CELL_TYPE_BLANK);
        }else if(value instanceof Date){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            cell.setCellValue(format.format((Date) value));
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
        }else if(value instanceof Number){
            cell.setCellValue(value.toString());
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
        }else if(value instanceof EnumType){
            Method method = value.getClass().getMethod("getDescription");
            method.setAccessible(true);

            cell.setCellValue(method.invoke(value, (Object[]) null).toString());
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
        }else{
            cell.setCellValue(value.toString());
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
        }
    }
}
