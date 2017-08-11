package com.txl.demo.controller;

import com.txl.demo.configration.RestConnection;
import com.txl.demo.dao.GroupInfo;
import com.txl.demo.dao.GroupInfoDao;
import com.txl.demo.version.ApiVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by TeaBee on 2017/7/27.
 */
@RestController
public class HelloController {

    @Autowired
    private RestConnection restConnection;

    @Autowired
    private GroupInfoDao groupInfoDao;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(method = RequestMethod.GET , value = "/mybatis")
    public Object getMybatis(){
        GroupInfo query = groupInfoDao.query("2");
        GroupInfo groupInfo = new GroupInfo();
        groupInfo.setGroupId("2");
        groupInfo.setGroupName("bbb");
        groupInfo.setFilePath("ddd");
        groupInfoDao.delete("2");
        groupInfoDao.insert(groupInfo);

        groupInfo.setGroupName("txl");
        groupInfoDao.update(groupInfo);

        restConnection.connect();
        return query;
    }

    @RequestMapping(method = RequestMethod.GET , value = "/hello")
    public Object getHello(){
        return "Hello";
    }

    @RequestMapping(method = RequestMethod.GET , value = "/rest")
    public Object getRest(){
        ResponseEntity<Object> entity = restTemplate.getForEntity("http://api.avatardata.cn/MobilePlace/LookUp?key=023d7e6345e341feabee16dbb458eec0&mobileNumber=13021671512", Object.class);
        Object body = entity.getBody();
        return body;
    }

    @RequestMapping(method = RequestMethod.GET , value = "/redisSet")
    public Object getRedis(){

        redisTemplate.opsForValue().set("tianxiaolong","salory:15000");
        return "Get";
    }

    @RequestMapping(method = RequestMethod.GET , value = "/redisGet")
    public Object getRedisGet(){

        if (redisTemplate.hasKey("tianxiaolong")){
            Object tianxiaolong = redisTemplate.opsForValue().get("tianxiaolong");
            return tianxiaolong;
        }
        return "failed";
    }


    @RequestMapping(method = RequestMethod.GET , value = "/test")
    @ApiVersion(1.3)
    public Object getTestV13(){
        return "test V13";
    }

    @RequestMapping(method = RequestMethod.GET , value = "/test")
    @ApiVersion(value = 1.1,expireDate = "2016-6-8 0:0:0")
    public Object getTestV11(){
        return "test V11";
    }

    @RequestMapping(method = RequestMethod.GET , value = "/test")
    @ApiVersion(value = 1.5,isUsing = false)
    public Object getTestV15(){
        return "test V15";
    }

    @RequestMapping(method = RequestMethod.GET , value = "/test")
    @ApiVersion(2.1)
    public Object getTestV21(){
        return "test V21";
    }

    public RestConnection getRestConnection() {
        return restConnection;
    }

    public void setRestConnection(RestConnection restConnection) {
        this.restConnection = restConnection;
    }
}
