package com.school.forum.service;

import com.school.forum.dto.PaginationDTO;
import com.school.forum.dto.QuestionDTO;
import com.school.forum.entity.Question;
import com.school.forum.entity.User;
import com.school.forum.mapper.QuestionMapper;
import com.school.forum.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 业务逻辑组装
     * @param page
     * @param size
     * @return
     */

    public PaginationDTO list(Integer page, Integer size) {

        /**
         * page : 页数 第几页
         * offset  size * (page - 1)
         * index
         *   页数   all / size
         */
        Integer offset = size * (page - 1);
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();

        if(totalCount % size == 0){
            paginationDTO.setTotalPage(totalCount / size);
        }else{
            paginationDTO.setTotalPage(totalCount / size + 1);
        }


        if(offset < 1){
            offset = 1;
        }
        if(offset > paginationDTO.getTotalPage()){
            offset = paginationDTO.getTotalPage();
        }

        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findByUserId(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);

            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);

        paginationDTO.setPagination(page,size);



        return paginationDTO;
    }

    public PaginationDTO listQuestion(Integer user_id,Integer page, Integer size) {

        Integer offset = size * (page - 1);
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.countByCreator(user_id);

        if(totalCount % size == 0){
            paginationDTO.setTotalPage(totalCount / size);
        }else{
            paginationDTO.setTotalPage(totalCount / size + 1);
        }


        if(offset < 1){
            offset = 1;
        }
        if(offset > paginationDTO.getTotalPage()){
            offset = paginationDTO.getTotalPage();
        }

        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findByUserId(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);

            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);

        paginationDTO.setPagination(page,size);



        return paginationDTO;
    }


}
