package com.school.forum;

import com.school.forum.entity.Question;
import com.school.forum.mapper.QuestionMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ForumApplication.class)
public class QuestionMapperTest {

    @Autowired
    private QuestionMapper questionMapper;

    @Test
    public void testInsertQuestion(){
        Question question = new Question("test","test",12L,12L,12,12,12,12,"test");
        questionMapper.insertQuestion(question);
    }

}
