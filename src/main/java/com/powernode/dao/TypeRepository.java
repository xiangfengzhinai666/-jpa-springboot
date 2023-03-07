package com.powernode.dao;

import com.powernode.po.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author 香风智乃
 * @className TypeRepository
 * @date 2023/2/26 11:28
 * @desciption: 分类持久层
 */

public interface TypeRepository extends JpaRepository<Type,Long> {

//    这里没有自带的方法，都需要自己去定义
    Type findByName(String name);

    @Query("select t from t_type t")
    List<Type> findTop(Pageable pageable);

}
