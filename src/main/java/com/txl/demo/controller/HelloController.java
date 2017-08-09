package com.txl.demo.controller;

import com.txl.demo.configration.RestConnection;
import com.txl.demo.dao.GroupInfo;
import com.txl.demo.dao.GroupInfoDao;
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

    public RestConnection getRestConnection() {
        return restConnection;
    }

    public void setRestConnection(RestConnection restConnection) {
        this.restConnection = restConnection;
    }
}
