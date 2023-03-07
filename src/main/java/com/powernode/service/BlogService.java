package com.powernode.service;

import com.powernode.po.Blog;
import com.powernode.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author 香风智乃
 * @className BlogService
 * @date 2023/2/27 10:37
 * @desciption: 博客业务类
 */

public interface BlogService {

    /**
     * 查询方法
     * @param id
     * @return
     */
    Blog getBlog(Long id);

    /**
     * 分页方法
     * @param pageable
     * @param blog
     * @return
     */
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

//    重载一个方法
    Page<Blog> listBlog(Pageable pageable);


    Page<Blog> listBlog(Long tagId,Pageable pageable);

    Page<Blog> listBlog(String query,Pageable pageable);

    /**
     * 新增方法
     * @param blog
     * @return
     */
    Blog saveBlog(Blog blog);

    /**
     * 更新方法
     * @param id
     * @param blog
     * @return
     */
    Blog updateBlog(Long id,Blog blog);

    /**
     * 删除方法
     * @param id
     */
    void deleteBlog(Long id);

    List<Blog> listRecommendBlogTop(Integer size);

    Blog getAndConvert(Long id);

    Map<String,List<Blog>> archiveBlog();

    Long countBlog();



}
