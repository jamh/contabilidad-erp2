/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feraz.polizas3.util;

import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author Ing. JAMH
 */
public class BeanPropertyCopyUtil {

    public static void copyProperties(Object src, Object dest,
            String... properties) throws IllegalAccessException,
            InvocationTargetException, NoSuchMethodException {
        for (String property : properties) {
            String[] arr = property.split(" ");
            String srcProperty;
            String destProperty;
            if (arr.length == 2) {
                srcProperty = arr[0];
                destProperty = arr[1];
            } else {
                srcProperty = property;
                destProperty = property;
            }
            BeanUtils.setProperty(dest, destProperty, BeanUtils.getProperty(
                    src, srcProperty));
        }
    }

}
