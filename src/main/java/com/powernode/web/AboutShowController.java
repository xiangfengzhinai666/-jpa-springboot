package com.powernode.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 香风智乃
 * @className AboutShowController
 * @date 2023/3/5 12:58
 * @desciption: 关于我的web展示
 */

@Controller
public class AboutShowController {

    @GetMapping("/about")
    public String about(){

        return "about";
    }
}
