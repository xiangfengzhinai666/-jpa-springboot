package com.powernode.web;

import com.powernode.po.Type;
import com.powernode.service.BlogService;
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

import java.util.List;

/**
 * @author 香风智乃
 * @className WebShowController
 * @date 2023/3/4 17:10
 * @desciption: 分类的web层
 */

@Controller
public class WebShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size=8,
            sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable Long id, Model model){

        List<Type> types = typeService.listTypeTop(10000);

        if(id == -1){
            id = types.get(0).getId();
        }

        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);

        model.addAttribute("types",types);
        model.addAttribute("page",blogService.listBlog(pageable,blogQuery));

        model.addAttribute("activeTypeId",id);

        return "types";
    }
}
