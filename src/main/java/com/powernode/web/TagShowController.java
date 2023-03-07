package com.powernode.web;

import com.powernode.po.Tag;
import com.powernode.service.BlogService;
import com.powernode.service.TagService;
import com.powernode.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author 香风智乃
 * @className WebShowController
 * @date 2023/3/4 17:10
 * @desciption: 分类的web层
 */

@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@PageableDefault(size=8,
            sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable Long id, Model model){

        List<Tag> tags = tagService.listTagTop(10000);

//        tags.size() > 0 && tags != null 这里加上不知道对不对
        if(id == -1 && tags.size() > 0 && tags != null){
            id = tags.get(0).getId();
        }

        model.addAttribute("tags",tags);
        model.addAttribute("page",blogService.listBlog(id,pageable));

        model.addAttribute("activeTagId",id);

        return "tags";
    }
}
