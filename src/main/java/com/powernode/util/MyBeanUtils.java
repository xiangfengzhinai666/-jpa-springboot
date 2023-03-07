package com.powernode.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 香风智乃
 * @className MyBeanUtils
 * @date 2023/3/1 17:37
 * @desciption: 工具类
 */

public class MyBeanUtils {

    public static String[] getNullPropertyNames(Object source){

        BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();
        List<String> nullPropertyNames = new ArrayList<>();

        for (PropertyDescriptor pd:pds
             ) {
            String propertyName = pd.getName();
            if(beanWrapper.getPropertyValue(propertyName)  == null){
                nullPropertyNames.add(propertyName);
            }
        }

        return nullPropertyNames.toArray(new String[nullPropertyNames.size()]);

    }
}
