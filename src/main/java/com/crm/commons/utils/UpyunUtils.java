package com.crm.commons.utils;

import main.java.com.UpYun;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * 又拍云工具类
 */
public class UpyunUtils{

    private final static String BUCKET_NAME = "kuliao";
    private final static String BUCKET_VIDEO_NAME = "hsl-video";
    private final static String OPERATOR_NAME = "unesmall";
    private final static String OPERATOR_PWD = "unesmall123456";

    //课程封面目录
    public final static String COURSE_COVER_DIR = "/course/cover/";
    //题库图片目录
    public final static String TEST_LIBRARY_DIR = "/testLibrary/cover/";
    //用户头像目录
    public final static String USER_HEAD_DIR = "/user/head/";
    //导师头像目录
    public final static String ADVISOR_HEAD_DIR = "/advisor/head/";

    //导师头像目录
    public final static String EMOTION_TEST_DIR = "/emotion/test/";

    //录音目录
    public final static String CALL_RECORD_LU_YIN = "/callRecord/luyin/";


    //录音目录
    public final static String RECORD_LU_YIN_DIR = "/record/luyin/";

    //wxdb目录
    public final static String RECORD_WX_DB_DIR = "/record/wxdb/";
    //会员等级图片目录
    public final static String LEVEL_COVER_DIR = "/level/cover/";
    //支付方式图片目录
    public final static String PAYMENT_TYPE_COVER_DIR = "/paymentType/cover/";
    //富文本资源目录
    public final static String UEDITOR_RESOURCE_DIR = "/ueditor/resource/";
    //课程视频目录
    public final static String COURSE_MEDIA_DIR = "/course/media/";
    //访问外链
    public final static String URL = "http://kuliao.b0.upaiyun.com";
    //视频外链
    public final static String VIDEO_URL = "http://hsl-video.test.upcdn.net";
//    public final static String VIDEO_URL = "http://media.ishsls.com";

    //UpYun
    private static UpYun upyun;

    //自定义客户端
    private static UpYun videoUpyun;

    //UpYun
    private static UpYunTest upYunTest;

    private UpyunUtils(){

    }

    private static UpYunTest getUpyunTest() {
        if (upYunTest == null) {
            upYunTest = new UpYunTest(BUCKET_NAME, OPERATOR_NAME, OPERATOR_PWD);
            upYunTest.setDebug(false);
            //2分钟超时时长
            upYunTest.setTimeout(120);
        }

        return upYunTest;
    }

    private static UpYun getVideoUpyun(){
        if(videoUpyun == null){
            videoUpyun = new UpYun(BUCKET_VIDEO_NAME, OPERATOR_NAME, OPERATOR_PWD);
            videoUpyun.setDebug(false);
            //5分钟超时时长
            videoUpyun.setTimeout(300);
        }

        return videoUpyun;
    }

    private static UpYun getUpyun(){
        if(upyun == null){
            upyun = new UpYun(BUCKET_NAME, OPERATOR_NAME, OPERATOR_PWD);
            upyun.setDebug(false);
            //5分钟超时时长
            upyun.setTimeout(300);
        }

        return upyun;
    }

    public static boolean upload(String uploadPath, CommonsMultipartFile file){
        if(StringUtils.isBlank(uploadPath)){
            throw new IllegalArgumentException("上传路径不能为空");
        }

        return getUpyun().writeFile(uploadPath, file.getBytes());
    }

    public static Map<String, String> getFileInfo(String uploadPath){
        if(StringUtils.isBlank(uploadPath)){
            throw new IllegalArgumentException("上传路径不能为空");
        }

        return getUpyun().getFileInfo(uploadPath);
    }

    public static boolean delete(String uploadPath){
        if(StringUtils.isBlank(uploadPath)){
            throw new IllegalArgumentException("上传路径不能为空");
        }

        return getUpyun().deleteFile(uploadPath);
    }

    /**
     * 上传视频
     */
    public static boolean uploadVideo(String uploadPath, CommonsMultipartFile file){
        if(StringUtils.isBlank(uploadPath)){
            throw new IllegalArgumentException("上传路径不能为空");
        }

        return getVideoUpyun().writeFile(uploadPath, file.getBytes());
    }

    /**
     * 获取视频信息
     */
    public static Map<String, String> getVideoFileInfo(String uploadPath){
        if(StringUtils.isBlank(uploadPath)){
            throw new IllegalArgumentException("上传路径不能为空");
        }

        return getVideoUpyun().getFileInfo(uploadPath);
    }

    /**
     * 删除视频
     */
    public static boolean deleteVideo(String uploadPath){
        if(StringUtils.isBlank(uploadPath)){
            throw new IllegalArgumentException("上传路径不能为空");
        }

        return getVideoUpyun().deleteFile(uploadPath);
    }

    public static boolean writerFile(String filePath, InputStream inputStream, boolean auto, Map<String, String> params) throws IOException {

        if (StringUtils.isBlank(filePath)) {
            throw new IllegalArgumentException("上传路径不能为空");
        }
        return getUpyunTest().writerFile(filePath, inputStream, auto, params);

    }


    /**
     * 上传文件
     *
     * @param filePath 文件路径（包含文件名）
     * @param datas    文件内容
     * @return true or false
     */
    public static boolean writeFile(String filePath, byte[] datas) {
        return getUpyunTest().writeFile(filePath, datas);
    }
}