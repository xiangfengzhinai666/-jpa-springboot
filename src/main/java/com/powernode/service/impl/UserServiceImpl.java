package com.powernode.service.impl;

import com.powernode.dao.UserRepository;
import com.powernode.po.User;
import com.powernode.service.UserService;
import com.powernode.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 香风智乃
 * @className UserServiceImpl
 * @date 2023/2/25 12:15
 * @desciption: 业务实现类
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));

        return user;
    }
}
