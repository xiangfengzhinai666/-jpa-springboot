package com.powernode.web;

import com.powernode.exception.NotFoundException;
import com.powernode.service.BlogService;
import com.powernode.service.TagService;
import com.powernode.service.TypeService;
import com.powernode.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 香风智乃
 * @className IndexController
 * @date 2023/2/24 9:41
 * @desciption: 控制器
 */

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;


    @GetMapping("/")
    public String index(@PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model){

        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listTypeTop(6));

        model.addAttribute("tags",tagService.listTag(10));

        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));

        //        int i = 9/0;
//        String blog = null;
//        if(blog == null){
//            throw new NotFoundException("博客不存在，是不是输入错误了呢╮(╯3╰)╭");
//        }

//        System.out.println("-------index--------");
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        model.addAttribute("blog",blogService.getAndConvert(id));
        return "blog";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, @RequestParam String query, Model model){

        model.addAttribute("page",blogService.listBlog("%"+query+"%",pageable));
        model.addAttribute("query",query);

        return "search";
    }

    @GetMapping("/footer/newBlog")
    public String newBlogs(Model model){

        model.addAttribute("newblogs",blogService.listRecommendBlogTop(3));

        return "_fragments :: newBlogList";
    }


}
