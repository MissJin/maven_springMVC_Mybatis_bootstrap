package com.hcj.demo.dao;

import com.hcj.demo.model.User;
import com.hcj.demo.model.UserExample;
import java.util.List;

import com.hcj.demo.service.baseGeneric.GenericDao;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends GenericDao<User, Integer>{
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}