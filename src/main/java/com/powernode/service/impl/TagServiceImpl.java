package com.powernode.service.impl;

import com.powernode.dao.TagRepository;
import com.powernode.exception.NotFoundException;
import com.powernode.po.Tag;
import com.powernode.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 香风智乃
 * @className TagServiceImpl
 * @date 2023/2/28 14:16
 * @desciption: 标签的实现类
 */

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagRepository.findOne(id);
    }


    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTag(String ids) {

        return tagRepository.findAll(convertToList(ids));
    }

    @Override
    public List<Tag> listTag(Integer size) {

        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");

        Pageable pageable = new PageRequest(0,size,sort);
        return tagRepository.findTop(pageable);
    }

//    这个方法是直接赋值的
    private List<Long> convertToList(String ids){
        List<Long> list = new ArrayList<>();

        if(!"".equals(ids) && ids != null){
            String[] idArray = ids.split(",");
            for(int i = 0;i < idArray.length;i++){
                list.add(new Long(idArray[i]));
            }
        }
        return list;
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag type) {
        Tag one = tagRepository.findOne(id);

        if(one == null){
            throw new NotFoundException("不存在该标签");
        }

//        把type复制到one上
        BeanUtils.copyProperties(type,one);
        return tagRepository.save(one);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.delete(id);
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");

        Pageable pageable = new PageRequest(0,size,sort);
        return tagRepository.findTop(pageable);
    }
}
