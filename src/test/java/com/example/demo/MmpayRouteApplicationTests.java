package com.example.demo;

import com.ylzinfo.MmpayRouteApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MmpayRouteApplication.class)
class MmpayRouteApplicationTests {

    String url = "http://localhost:8080/mmpay-web/json";

    @Test
    void contextLoads() {
    }

}
