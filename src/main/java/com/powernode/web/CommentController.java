package com.powernode.web;

import com.powernode.po.Comment;
import com.powernode.po.User;
import com.powernode.service.BlogService;
import com.powernode.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @author 香风智乃
 * @className CommentController
 * @date 2023/3/3 20:24
 * @desciption: 评论web类
 */

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

//    @Value("${comment.avatar2}")
//    private String avatar2;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments",commentService.listCommentByBlogId(blogId));

        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));

        User user = (User) session.getAttribute("user");

//        使用管理员的头像
        if(user != null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true); //把这个改成true，就是前端会进行判断是否是管理员
//            comment.setNickname(user.getNickname()); //从后端获取nickname

        }else{
//            没有登录的话就用和普通用户一样的方式
            comment.setAvatar(avatar);
        }

//        comment.setAvatar(avatar);  卧槽你干嘛，前面修改了，这里又改回去，肯定是一样的图呀，我吐了
        commentService.saveComment(comment);
        return "redirect:/comments/"+ comment.getBlog().getId();
    }
}
