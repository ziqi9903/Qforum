package com.school.forum.mapper;

import com.school.forum.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface QuestionMapper {

    void insertQuestion(Question question);

    @Select({
            "select * from question "
    })
    List<Question> list();
}
