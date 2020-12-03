package com.school.forum.controller;

import com.school.forum.dto.PaginationDTO;
;
import com.school.forum.entity.User;
import com.school.forum.mapper.UserMapper;
import com.school.forum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/index")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "7") Integer size) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();

                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("userSession", user);
                    }
                    break;
                }
            }
        }

        PaginationDTO question = questionService.list(page,size);
        model.addAttribute("question",question);
            return "index";
    }

}