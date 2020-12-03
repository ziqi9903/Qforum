package com.school.forum.controller;

import com.school.forum.entity.Question;
import com.school.forum.entity.User;
import com.school.forum.mapper.QuestionMapper;
import com.school.forum.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        return "/login/publish";
    }

    @PostMapping("/publish")
    public String addPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            HttpServletRequest request,
            Model model){

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        if (StringUtils.isBlank(title)) {
            model.addAttribute("error", "标题不能为空");
            return "login/publish";
        }
        if (StringUtils.isBlank(description)) {
            model.addAttribute("error", "问题补充不能为空");
            return "login/publish";
        }
        if (StringUtils.isBlank(tag)) {
            model.addAttribute("error", "标签不能为空");
            return "login/publish";
        }

        User user = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                user = userMapper.findByToken(token);
                if (user != null){
                    request.getSession().setAttribute("userSession",user);
                }
                break;
            }
        }
        if(user == null) {
            model.addAttribute("error", "用户未登录,请先登录");
            return "login/publish";
        }
            Question question = new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreator(user.getUser_id());
            question.setGmt_create(System.currentTimeMillis());
            question.setGmt_modified(question.getGmt_create());
            questionMapper.insertQuestion(question);
            return "redirect:/index";


    }
}
