package com.school.forum.mapper;

import com.school.forum.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface QuestionMapper {

    void insertQuestion(Question question);

}
