package com.ylzinfo.util;

import org.springframework.context.ApplicationContext;

/**
 * ApplicaitonContext工具类
 */
public class ApplicationContextUtils {

    private static ApplicationContext context;

    public static void setContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    public static <T> T getBean(Class<T> t) {
        return context.getBean(t);
    }
}