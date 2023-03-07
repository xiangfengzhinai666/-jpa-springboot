package com.powernode.service.impl;

import com.powernode.dao.BlogRepository;
import com.powernode.exception.NotFoundException;
import com.powernode.po.Blog;
import com.powernode.service.BlogService;
import com.powernode.util.MarkdownUtils;
import com.powernode.util.MyBeanUtils;
import com.powernode.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * @author 香风智乃
 * @className BlogServiceImpl
 * @date 2023/2/27 10:41
 * @desciption: 博客实现类
 */

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<>();

                if(!"".equals(blog.getTitle()) && blog.getTitle() != null){
                    predicates.add(cb.like(root.<String>get("title"),"%"+blog.getTitle()+"%"));
                }

                if(blog.getTypeId() != null ){

                    predicates.add(cb.equal(root.get("type").get("id"),blog.getTypeId()));
                }

                if(blog.isRecommend()){
                    predicates.add(cb.equal(root.<Boolean>get("recommend"),blog.isRecommend()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {

        return blogRepository.findAll(pageable);

    }

    @Override
    public Page<Blog> listBlog(Long tagId, Pageable pageable) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join join = root.join("tags");

                return cb.equal(join.get("id"),tagId);
            }
        },pageable);
    }

    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return blogRepository.findByQuery(query,pageable);
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {

        if(blog.getId() == null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        }else{
            blog.setUpdateTime(new Date());
        }
        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog one = blogRepository.getOne(id);
        if(one == null){
            throw new NotFoundException("该博客不存在");
        }
        BeanUtils.copyProperties(blog,one, MyBeanUtils.getNullPropertyNames(blog));
        one.setUpdateTime(new Date());

        return blogRepository.save(one);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.delete(id);
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {

        Sort sort = new Sort(Sort.Direction.DESC,"updateTime");

        Pageable pageable = new PageRequest(0,size,sort);


        return blogRepository.findTop(pageable);
    }

    @Transactional
    @Override
    public Blog getAndConvert(Long id) {
        Blog blog = blogRepository.findOne(id);

        if(blog == null){
            throw new NotFoundException("该博客不存在");
        }

        Blog b = new Blog();
        BeanUtils.copyProperties(blog,b);

        String content = b.getContent();

        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));

//        实现浏览次数的累加
        blogRepository.updateViews(id);
        return b;
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {

        List<String> years = blogRepository.findGroupYear();

        Map<String,List<Blog>> map = new HashMap<>();
        for (String year: years) {
            map.put(year,blogRepository.findByYear(year));
        }
        return map;
    }

//    统计一共有多少条
    @Override
    public Long countBlog() {
        return blogRepository.count();
    }
}
