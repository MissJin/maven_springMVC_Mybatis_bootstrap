package com.hcj.demo.service.impl;

import com.hcj.demo.dao.UserMapper;
import com.hcj.demo.model.User;
import com.hcj.demo.model.UserExample;
import com.hcj.demo.service.UserService;
import com.hcj.demo.service.baseGeneric.GenericDao;
import com.hcj.demo.service.baseGeneric.GenericServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by superadmin on 2017/2/13.
 */
@Service("testService")
public class UserServiceImpl extends GenericServiceImpl<User,Integer> implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public GenericDao<User, Integer> getDao() {
        return userMapper;
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int update(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int delete(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }





    public List<User> selectList() {
        UserExample example = new UserExample();
        return userMapper.selectByExample(example);
    }
    public List<User> selectListByExample(UserExample example) {
        return userMapper.selectByExample(example);
    }
}
