package com.school.forum;

import com.school.forum.entity.User;
import com.school.forum.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ForumApplication.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsertUser(){
        User user = new User("12","12","12",12L,12L,"12","12");
        userMapper.insertUser(user);
    }

    @Test
    public void testSelectByToken(){
        System.out.println(userMapper.findByToken("9e46c243-e5bb-4355-8e8e-40692ad5c112"));
    }

    @Test
    public void  testselectUserName(){
        System.out.println(userMapper.selectUserName(24));
    }

    @Test
    public void testFindByUserId(){
        System.out.println(userMapper.findByUserId(48));
    }


}
