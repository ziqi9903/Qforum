package com.school.forum.mapper;

import com.school.forum.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface QuestionMapper {

    void insertQuestion(Question question);

    @Select({
            "select * from question limit #{offset}, #{size}"
    })
    List<Question> list(@Param(value = "offset") Integer offset ,
                        @Param(value = "size") Integer size);

    @Select("select count(1) from question ")
    Integer count();
}
