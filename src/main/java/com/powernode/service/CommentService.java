package com.powernode.service;

import com.powernode.po.Comment;

import java.util.List;

/**
 * @author 香风智乃
 * @className CommentService
 * @date 2023/3/3 20:33
 * @desciption: 评论接口
 */

public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);


}
