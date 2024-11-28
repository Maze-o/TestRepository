package com.jcsg.test.book.controller;

import com.jcsg.test.book.domain.dto.BookDto;
import com.jcsg.test.book.domain.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookServiceImpl bookServiceImpl;

    // 도서 추가 페이지
    @GetMapping("/bookAdd")
    public String bookAdd() {
        return "book/bookAdd";
    }

    // 도서 추가 요청 처리
    @PostMapping("/bookAdd")
    public String bookAdd_post(BookDto bookDto) {
        bookServiceImpl.addBook(bookDto); // 서비스 호출
        return "redirect:/book/bookList";
    }

    // 도서 전체 조회 처리
    @GetMapping("/bookList")
    public String bookList(Model model) {
        model.addAttribute("books", bookServiceImpl.getBooks()); // 서비스 호출
        return "book/bookList"; // 뷰 반환
    }


    // 도서 단건 조회 처리
    @GetMapping("/bookRead/{bookCode}")
    public String bookRead(@PathVariable("bookCode") Long bookCode, Model model) {
        model.addAttribute("bookDto", bookServiceImpl.getBook(bookCode));
        return "book/bookRead";
    }


    // 도서 수정 처리
    @GetMapping("/bookUpdate/{bookCode}")
    public String bookUpdate(@PathVariable("bookCode") Long bookCode, Model model) {
        model.addAttribute("bookDto", bookServiceImpl.getBook(bookCode)); // 서비스 호출
        return "book/bookUpdate";
    }
    @PostMapping("/bookUpdate")
    public String bookUpdate(BookDto bookDto) {
        bookServiceImpl.updateBook(bookDto); // 서비스 호출
        return "redirect:/book/bookList";
    }

    // 도서 삭제 처리
    @PostMapping("/bookDelete")
    public String bookDelete(@RequestParam("bookCode") Long bookCode) {
        bookServiceImpl.deleteBook(bookCode); // 도서 삭제
        return "redirect:/book/bookList"; // 삭제 후 목록 페이지로 이동
    }

}
