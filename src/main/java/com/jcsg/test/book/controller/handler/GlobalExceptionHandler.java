package com.jcsg.test.book.controller.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * IllegalArgumentException 처리
     * @param exception 발생한 예외
     * @return 에러 페이지와 데이터를 전달
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handleIllegalArgumentException(IllegalArgumentException exception){
        ModelAndView errorPage = new ModelAndView("error");
        errorPage.addObject("errorMessage", exception.getMessage());
        return errorPage;
    }

    /**
     * 기타 Exception 처리
     * @param exception 발생한 예외
     * @return 에러 페이지와 데이터를 전달
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleGeneralException(Exception exception) {
        ModelAndView errorPage = new ModelAndView("error");
        errorPage.addObject("errorMessage", "예기치 못한 오류가 발생했습니다.");
        return errorPage;
    }

}
