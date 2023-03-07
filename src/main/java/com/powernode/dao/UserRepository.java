package com.powernode.dao;

import com.powernode.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 香风智乃
 * @className UserRepository
 * @date 2023/2/25 12:17
 * @desciption: 持久层
 */

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username,String password);

}
