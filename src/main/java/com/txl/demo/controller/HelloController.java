package com.txl.demo.controller;

import com.txl.demo.configration.RestConnection;
import com.txl.demo.dao.GroupInfo;
import com.txl.demo.dao.GroupInfoDao;
import com.txl.demo.version.ApiVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TeaBee on 2017/7/27.
 */
@RestController
public class HelloController {

    @Autowired
    private RestConnection restConnection;

    @Autowired
    private GroupInfoDao groupInfoDao;

    @RequestMapping(method = RequestMethod.GET , value = "/hello")
    @ApiVersion(1.1)
    public Object getHello(){
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
    @ApiVersion(2)
    public Object getHelloV2(){
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
    @ApiVersion(value = 2.3,isUsing = true, expireDate = "2018-5-1 0:0:0")
    public Object getHelloV3(){
        return "hello";
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
