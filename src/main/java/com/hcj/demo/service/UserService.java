package com.hcj.demo.service;

import com.hcj.demo.model.User;
import com.hcj.demo.model.UserExample;
import com.hcj.demo.service.baseGeneric.GenericService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by superadmin on 2017/2/13.
 */

public interface UserService extends GenericService<User,Integer> {

    List<User> selectListByExample(UserExample example);
}
