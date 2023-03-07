package com.powernode.service.impl;

import com.powernode.dao.TypeRepository;
import com.powernode.exception.NotFoundException;
import com.powernode.po.Type;
import com.powernode.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 香风智乃
 * @className TypeServiceImpl
 * @date 2023/2/26 11:25
 * @desciption: 实现类
 */

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

//    增删改查都放在事务里面
    @Transactional
    @Override
    public Type saveType(Type type) {
        Type save = typeRepository.save(type);
        return save;
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        Type one = typeRepository.findOne(id);
        return one;
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {

        return typeRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type one = typeRepository.findOne(id);

        if(one == null){
            throw new NotFoundException("不存在该类型");
        }

//        这个方法的意思就是把type里面的对象复制到one里面
        BeanUtils.copyProperties(type,one);

        return typeRepository.save(one);
    }

//    删除在mybatis应该是使用int作为返回值的，jpa没有返回int
    @Transactional
    @Override
    public void deleteType(Long id) {

        typeRepository.delete(id);
    }

//    通过name找寻，判断其唯一性
    @Override
    public Type getTypeByName(String name) {
        Type byName = typeRepository.findByName(name);

        return byName;
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {

        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");

        Pageable pageable = new PageRequest(0,size,sort);
        return typeRepository.findTop(pageable);
    }
}
