package com.powernode.service;

import com.powernode.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author 香风智乃
 * @className TypeService
 * @date 2023/2/26 11:22
 * @desciption: 业务分类接口
 */

public interface TypeService {

//    新增
    Type saveType(Type type);

//    查询
    Type getType(Long id);

//    分页查询
    Page<Type> listType(Pageable pageable);

//    更新
    Type updateType(Long id,Type type);

//    删除
    void deleteType(Long id);

//    通过名称查询name
    Type getTypeByName(String name);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);
}
