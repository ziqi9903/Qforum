package com.school.forum.controller;

import com.school.forum.dto.AccessTokenDTO;
import com.school.forum.dto.GithubUser;
import com.school.forum.entity.User;
import com.school.forum.mapper.UserMapper;
import com.school.forum.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    /**
     * param 用于接收按钮提交的事件
     * code 和 state 用来记录 /callback get 请求时的提交的值
     * @param code 获取网页提交时的code
     * @param state 获取网页提交时的state
     * @return
     * 页面 uri = http://localhost:8081/callback
     */


    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String clientUri;

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(clientUri);
        accessTokenDTO.setState(state);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);

        if (githubUser != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            //请求头问题,已处理
            user.setName(githubUser.getName());
            user.setAccount_id(String.valueOf(githubUser.getId()));
            user.setToken(token);
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            user.setBio("zi");
            user.setAvatar_url("qi");

            userMapper.insertUser(user);

            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60 * 60 * 24 * 30 * 6);
            response.addCookie(cookie);
            return "redirect:index";
        } else {
            /**
             * 登录失败,重新登录
             */
            return "redirect:index";
        }
    }
}
