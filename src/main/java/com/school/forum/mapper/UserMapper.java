package com.school.forum.mapper;

import com.school.forum.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    void insertUser(User user);

    User findByToken(@Param(value = "token") String token);

    String selectUserName(int id);

    User findByUserId(Integer creator);
}
