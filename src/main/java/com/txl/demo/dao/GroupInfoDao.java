package com.txl.demo.dao;


import org.apache.ibatis.annotations.Mapper;

/**
 * Created by TeaBee on 2017/7/4.
 */
@Mapper
public interface GroupInfoDao {

    void insert(GroupInfo groupInfo);

    GroupInfo query(String groupId);

    void update(GroupInfo groupInfo);

    void delete(String groupId);

}
