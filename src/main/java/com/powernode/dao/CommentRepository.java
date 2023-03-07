package com.powernode.dao;

import com.powernode.po.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 香风智乃
 * @className CommentRepository
 * @date 2023/3/3 20:36
 * @desciption: 持久层接口
 */

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}
