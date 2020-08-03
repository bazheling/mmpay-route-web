package com.ylzinfo.controller;

import com.ylzinfo.dao.entity.GirlsInfo;
import com.ylzinfo.dao.mappers.GirlsInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
@RequestMapping("/mmpay")
public class MmpayRouteController {

    @Autowired
    private GirlsInfoMapper girlsInfoMapper;
    @RequestMapping("getuser")
    @ResponseBody
    public GirlsInfo getUser() {
        GirlsInfo girlsInfo = girlsInfoMapper.selectByPrimaryKey(Integer.parseInt("1"));
        return girlsInfo;
    }

    @RequestMapping("route")
    @ResponseBody
    public GirlsInfo route() {
        GirlsInfo girlsInfo = girlsInfoMapper.selectByPrimaryKey(Integer.parseInt("1"));
        return girlsInfo;
    }
    @RequestMapping("getuser1")
    @ResponseBody
    public String getUser1() {
        return "index";
    }
    @RequestMapping("hello")
    public String hello() {
        return "index";
    }
}