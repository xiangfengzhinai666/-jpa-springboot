package com.powernode.dao;

import com.powernode.po.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author 香风智乃
 * @className TagRepository
 * @date 2023/2/28 14:17
 * @desciption: 持久层,一般的方法是不需要书写的，只需要书写Repository里面没有的就行
 */

public interface TagRepository extends JpaRepository<Tag,Long> {

    Tag findByName(String name);

    @Query("select t from t_tag t")
    List<Tag> findTop(Pageable pageable);
}
