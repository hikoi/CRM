package com.crm.commons.utils;

import com.crm.commons.consts.Constants;
import com.crm.core.account.entity.Permission;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wah.doraemon.security.exception.UtilsException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
public class ScanUtils{

    private static final String RESOURCE_PATTERN = "/**/*.class";

    private static final ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

    public static List<Class> findBeans(String basePackage, List<Class<? extends Annotation>> annotations){
        if(StringUtils.isBlank(basePackage)){
            throw new UtilsException("扫描的包名不能为空");
        }

        try{
            List<Class> classes = new ArrayList<Class>();
            //包类路径
            String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(basePackage) + RESOURCE_PATTERN;
            //类
            Resource[] resources = resolver.getResources(pattern);
            MetadataReaderFactory factory = new CachingMetadataReaderFactory(resolver);

            for(Resource resource : resources){
                if(resource.isReadable()){
                    //获取类
                    MetadataReader reader = factory.getMetadataReader(resource);
                    String className = reader.getClassMetadata().getClassName();
                    Class clazz = Class.forName(className);

                    if(annotations != null && !annotations.isEmpty()){
                        for(Class annotationClass : annotations){
                            if(clazz.getAnnotation(annotationClass) != null){
                                classes.add(clazz);
                                break;
                            }
                        }
                    }else{
                        classes.add(clazz);
                    }
                }
            }

            return classes;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }
    }

    public static List<Annotation> findAnnotations(String basePackage, List<Class<? extends Annotation>> annotations){
        if(StringUtils.isBlank(basePackage)){
            throw new UtilsException("扫描的包名不能为空");
        }

        try{
            List<Annotation> target = new ArrayList<Annotation>();
            //包类路径
            String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(basePackage) + RESOURCE_PATTERN;
            //类
            Resource[] resources = resolver.getResources(pattern);
            MetadataReaderFactory factory = new CachingMetadataReaderFactory(resolver);

            for(Resource resource : resources){
                if(resource.isReadable()){
                    //获取类
                    MetadataReader reader = factory.getMetadataReader(resource);
                    String className = reader.getClassMetadata().getClassName();
                    Class clazz = Class.forName(className);

                    if(annotations != null && !annotations.isEmpty()){
                        for(Class annotationClass : annotations){
                            Annotation annotation = clazz.getAnnotation(annotationClass);

                            if(annotation != null){
                                target.add(annotation);
                            }
                        }
                    }else{
                        Annotation[] classAnnotations = clazz.getAnnotations();

                        if(classAnnotations != null && classAnnotations.length > 0){
                            target.addAll(Arrays.asList(classAnnotations));
                        }
                    }
                }
            }

            return target;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }
    }

    public static List<Permission> findPermission(String basePackage){
        if(StringUtils.isBlank(basePackage)){
            throw new UtilsException("扫描的包名不能为空");
        }

        try{
            List<Class> classes = findBeans(basePackage, Constants.API_ANNOTATIONS);

            List<Permission> permissions = new ArrayList<Permission>();

            if(classes != null && !classes.isEmpty()){
                //遍历
                for(Class clazz : classes){
                    RequestMapping mapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
                    String[] urls = mapping.value();

                    for(Method method : clazz.getDeclaredMethods()){
                        RequestMapping methodMapping = (RequestMapping) method.getAnnotation(RequestMapping.class);
                        String[] methodUrls = methodMapping.value();
                        RequestMethod[] methods = methodMapping.method();

                        for(String url : urls){
                            for(String methodUrl : methodUrls){
                                for(RequestMethod requestMethod : methods){
                                    Permission permission = new Permission();
                                    permission.setUrl(url + methodUrl);
                                    permission.setMethod(requestMethod);
                                    permissions.add(permission);
                                }
                            }
                        }
                    }
                }
            }

            return permissions;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }
    }
}
