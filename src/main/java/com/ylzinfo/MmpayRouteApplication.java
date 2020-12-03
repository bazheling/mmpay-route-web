package com.ylzinfo;

import com.ylzinfo.util.ApplicationContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author lcl
 * @date 2020/12/3
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)

public class MmpayRouteApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MmpayRouteApplication.class, args);
        ApplicationContextUtils.setContext(context);
    }

}
