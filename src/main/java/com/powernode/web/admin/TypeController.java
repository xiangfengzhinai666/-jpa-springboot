package com.powernode.web.admin;

import com.powernode.po.Type;
import com.powernode.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author 香风智乃
 * @className TypeController
 * @date 2023/2/26 11:40
 * @desciption: type的web层
 */

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

//    设置pageable 根据倒叙的方式来排序
    @GetMapping("/types")
    public String types(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                                   Pageable pageable, Model model){

        model.addAttribute("page",typeService.listType(pageable));

        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model){

        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String post(@Validated Type type, BindingResult result, RedirectAttributes attributes){

        Type t1 = typeService.getTypeByName(type.getName());
//        唯一校验
        if(t1 != null){
            result.rejectValue("name","nameError","不能添加重复的分类");
        }

        if(result.hasErrors()){
            return "admin/types-input";
        }

        Type type1 = typeService.saveType(type);

        if(type1 == null){
//
            attributes.addFlashAttribute("message","新增失败");
        }else{

            attributes.addFlashAttribute("message","新增成功");

        }
        return "redirect:/admin/types";
    }


    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){

        model.addAttribute("type",typeService.getType(id));

        return "admin/types-input";
    }

//    分类返回的方法
    @PostMapping("/types/{id}")
    public String editPost(@Validated Type type,BindingResult result,@PathVariable Long id,RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if(type1 != null){
            result.rejectValue("name","nameError","不能添加重复的分类");
        }

        if(result.hasErrors()){
            return "admin/types-input";
        }

        Type t = typeService.updateType(id, type);
        if(t == null){
            attributes.addFlashAttribute("message","更新失败");
        }else{
            attributes.addFlashAttribute("message","更新成功");
        }

        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }
}
