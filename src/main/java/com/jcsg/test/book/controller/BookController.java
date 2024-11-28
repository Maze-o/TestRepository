package com.jcsg.test.book.controller;

import com.jcsg.test.book.dto.BookDto;
import com.jcsg.test.book.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookServiceImpl bookServiceImpl;

    // 도서 추가 페이지
    @GetMapping("/bookAdd")
    public void bookAdd() {

    }

    // 도서 추가 post요청 시 Service로 로직 처리
    // 단건 추가나 단건 수정은 JpaRespository의 save()함수로 처리 가능
    @PostMapping("/bookAdd")
    public String bookAdd_post(BookDto bookDto) {
        // 도서 추가 시 예외처리 (빈칸만) DB에 대한 처리는 service로직에서 처리
        // 도서이름이 비었을 시
        if (!bookDto.getBookName().trim().isEmpty()) {

        }
        // 작성자가 비었을 시
        if (!bookDto.getPublisher().trim().isEmpty()) {

        }
        // 도서코드가 비었을 시
        if (!bookDto.getIsbn().trim().isEmpty()) {

        }

//      bookServiceImpl.bookAdd(bookDto);
        return "redirect:/book/bookList";
    }


    // 도서 리스트 페이지
    @GetMapping("/bookList")
    public void bookList(Model model) {
//        BookDto bookDto = bookServiceImpl.getBooks();

//        model.addAttribute("bookDto" , bookDto);

    }


    // 도서 단건 조회 페이지
    @GetMapping("/bookRead/{bookCode}")
    public void bookRead(
            @PathVariable("bookCode") String bookCode,
            Model model
    ) {

//        BookDto bookDto = bookServiceImpl.getBook(bookCode);

//        model.addAttribute("bookDto" , bookDto);
    }


    // 도서 내용 수정 페이지
    @GetMapping("/bookUpdate/{bookCode}")
    public void bookUpdate(
            @PathVariable("bookCode") String bookCode,
            Model model
    ) {
//        BookDto bookDto = bookServiceImpl.getBook(bookCode);

//        model.addAttribute("bookDto" , bookDto);
    }

    @PostMapping("/bookUpdate")
    public String bookUpdate(BookDto bookDto, Model model) {

        // 도서 수정 시 예외처리 (빈칸만) DB에 대한 처리는 service로직에서 처리
        // 도서이름이 비었을 시
        if (!bookDto.getBookName().trim().isEmpty()) {

        }
        // 작성자가 비었을 시
        if (!bookDto.getPublisher().trim().isEmpty()) {

        }
        // 도서코드가 비었을 시
        if (!bookDto.getIsbn().trim().isEmpty()) {

        }

//        BookDto dto = bookServiceImpl.updateBook(bookDto.getBookCode());
//        model.addAttribute("bookDto", dto);
        return "redirect:/book/read";
    }









}
