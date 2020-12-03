package com.ylzinfo;

import com.ylzinfo.ddjs.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author lcl
 * @date 2020/12/2
 */
@Component
public class RouteInitializer implements BeanFactoryPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(RouteInitializer.class);

    private static final String MMPAYROUTE_PROPERTIES_FILE = "config/mmpay-route.properties";

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        init();
    }

    public void init() {
        logger.info("ddjs admin system init...");
        try {
            // 初始化DdjsProperties常量类
            CommonUtils.loadProperties(MMPAYROUTE_PROPERTIES_FILE, MmpayRouteProperties.class);
//            Properties properties = new Properties();
//            // 使用ClassLoader加载properties配置文件生成对应的输入流
//            InputStream in = MmpayRouteApplication.class.getClassLoader().getResourceAsStream(MMPAYROUTE_PROPERTIES_FILE);
//            // 使用properties对象加载输入流
//            properties.load(in);
//            MmpayRouteProperties.jsonPath=properties.getProperty("jsonPath");
//            MmpayRouteProperties.getAppWay=properties.getProperty("getAppWay");
//            获取key对应的value值
            logger.info("mmpay route system init finish...");
        } catch (Exception e) {
            logger.error("mmpay route  init fail ", e);
        }
    }

    public void destroy() {
        logger.info("mmpay route system destroy...");
    }
}
