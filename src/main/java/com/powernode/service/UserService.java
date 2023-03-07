package com.powernode.service;

import com.powernode.po.User;

/**
 * @author 香风智乃
 * @className UserService
 * @date 2023/2/25 12:14
 * @desciption: 业务层
 */

public interface UserService {

//    检查用户名密码
    User checkUser(String username,String password);


}
