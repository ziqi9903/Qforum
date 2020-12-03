package com.school.forum.dto;

import java.util.ArrayList;
import java.util.List;

public class PaginationDTO {

    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;

    private Integer currentPage;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;


    /**
     *e
     * @param page 第几页
     * @param size 每页的个数
     */
    public void setPagination( Integer page, Integer size) {

        if(page < 1){
            page = 1;
        }
        if(page > totalPage){
            page = totalPage;
        }

        this.currentPage = page;

        pages.add(page);
        for(int i = 1; i <= 3; i++){
            if(page - i > 0){
                pages.add(0,page -i);
            }

            if (page + i <= totalPage){
                pages.add(page + i);
            }
        }

        //是否展示上一页
        if (page == 1){
            this.showPrevious = false;
        }else{
            this.showPrevious = true;
        }
        //是否展示下一页
        if(page == totalPage){
            this.showNext = false;
        }else{
            this.showNext = true;
        }

        //是否展示第一页
        if(pages.contains(1)){
            this.showFirstPage = false;
        }else{
            this.showFirstPage = true;
        }

        //是否展示最后一页
        if(pages.contains(totalPage)){
            this.showEndPage = false;
        }else{
            this.showEndPage = true;
        }




    }


    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

    public boolean isShowPrevious() {
        return showPrevious;
    }

    public void setShowPrevious(boolean showPrevious) {
        this.showPrevious = showPrevious;
    }

    public boolean isShowFirstPage() {
        return showFirstPage;
    }

    public void setShowFirstPage(boolean showFirstPage) {
        this.showFirstPage = showFirstPage;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public boolean isShowEndPage() {
        return showEndPage;
    }

    public void setShowEndPage(boolean showEndPage) {
        this.showEndPage = showEndPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

}
