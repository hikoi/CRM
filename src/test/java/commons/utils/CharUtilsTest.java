package commons.utils;

import com.crm.commons.utils.CharUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharUtilsTest{

    @Test
    public void toUnicode(){
        String content = "我是中文。";

        System.out.println(CharUtils.toUnicode(content));
    }

    @Test
    public void toChar(){
        String content = "\\u6211\\u662f\\u4e2d\\u6587\\u3002";

//        String content = "我是中文。";
//        content = CharUtils.toUnicode(content);

        System.out.println(CharUtils.toChar(content));
    }

    @Test
    public void reg(){
        List<String> contents = new ArrayList<String>();
        contents.add("我叫");
        contents.add("老师");

        StringBuffer regEx = new StringBuffer();
        for(int i = 0; i < contents.size(); i++){
            if(i > 0){
                regEx.append('|');
            }

            regEx.append(CharUtils.toUnicode(contents.get(i)));
        }

        System.out.println(regEx.toString());

        String content = "你好，我叫中文，我就是中文老师。";

        Pattern pattern = Pattern.compile(regEx.toString());
        Matcher matcher = pattern.matcher(content);

        System.out.println(matcher.find());
    }
}
