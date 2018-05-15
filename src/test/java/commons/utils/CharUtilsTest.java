package commons.utils;

import com.crm.commons.utils.CharUtils;
import org.junit.Test;

import java.text.MessageFormat;
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
        contents.add("操");
        contents.add("尼玛");
        contents.add("我靠");

        StringBuffer regEx = new StringBuffer();
        for(int i = 0; i < contents.size(); i++){
            if(i > 0){
                regEx.append('|');
            }

            regEx.append(CharUtils.toUnicode(contents.get(i)));
        }

        String content = "中文我靠中文";

        Pattern pattern = Pattern.compile(MessageFormat.format("[\\s\\S]*({0})+[\\s\\S]*", regEx));
        Matcher matcher = pattern.matcher(content);

//        System.out.println(matcher.find());
        System.out.println(matcher.matches());
    }
}
