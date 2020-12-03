package com.school.forum.controller;

import com.school.forum.dto.PaginationDTO;
import com.school.forum.entity.User;
import com.school.forum.mapper.UserMapper;
import com.school.forum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(
            @PathVariable(name = "action") String action
            ,HttpServletRequest request
            ,Model model
            ,@RequestParam(name = "page",defaultValue = "1") Integer page
            ,@RequestParam(name = "size",defaultValue = "7") Integer size){
        User user = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();

                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("userSession", user);
                    }
                    break;
                }
            }
        }

        if(user == null){
            model.addAttribute("error","未登录请先登录");
            return "redirect:/index";
        }

        if ("question".equals(action)){
            model.addAttribute("section","question");
            model.addAttribute("sectionName","我的提问");
        }else if ("reples".equals(action)){
            model.addAttribute("section","reples");
            model.addAttribute("sectionName","我的回复");
        }

        PaginationDTO pagination = questionService.listQuestion(user.getUser_id(), page,size);

        model.addAttribute("pagination",pagination);

        return "/question/profile";
    }

}
