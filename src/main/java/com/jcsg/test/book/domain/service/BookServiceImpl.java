package com.jcsg.test.book.domain.service;

import com.jcsg.test.book.domain.Entity.Book;
import com.jcsg.test.book.domain.dto.BookDto;
import com.jcsg.test.book.domain.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl {

    // BookRepository 의존성 주입
    @Autowired
    private BookRepository bookRepository;

    /**
     * DTO → 엔티티 변환
     * bookCode는 자동 생성되므로 포함하지 않는다.
     * @param bookDto 객체
     * @return 변환된 Book 엔티티 객체
     */
    private Book dtoToEntity(BookDto bookDto) {
        return Book.builder()
                .bookName(bookDto.getBookName())
                .publisher(bookDto.getPublisher())
                .isbn(bookDto.getIsbn())
                .build();
    }

    /**
     * DTO -> 엔티티 변환
     * 데이터베이스에서 생성된 bookCode를 클라이언트에 반환하기 때문에 bookCode가 포함된다.
     * @param book 엔티티 객체
     * @return 변환된 BookDto 객체
     */
    private BookDto entityToDto(Book book) {
        return BookDto.builder()
                .bookCode(book.getBookCode())
                .bookName(book.getBookName())
                .publisher(book.getPublisher())
                .isbn(book.getIsbn())
                .build();
    }

    /**
     * 도서 추가
     * 클라이언트로부터 받은 BookDto 데이터를 데이터베이스에 저장한다.
     * @param bookDto 저장할 도서 정보
     */
    public void addBook(BookDto bookDto) {
        // 필수 입력값 검증
        if(bookDto.getBookName().isEmpty() || bookDto.getPublisher().isEmpty() || bookDto.getIsbn().isEmpty()) {
            throw new IllegalArgumentException("모든 항목을 입력해야 합니다.");
        }
        // DTO -> 엔티티 변환 후 데이터베이스에 저장
        Book book = dtoToEntity(bookDto);
        bookRepository.save(book);
    }


    /**
     * 도서 목록 조회
     * 데이터베이스에 저장된 모든 도서를 조회하고 DTO 리스트로 변환하여 반환.
     * @return 모든 도서 정보를 포함한 DTO 리스트
     */
    public List<BookDto> getBooks() {
//        List<Book> books = bookRepository.findAll();
//        List<BookDto> bookDtos = new ArrayList<>();
//
//        for (Book book : books) {
//            bookDtos.add(entityToDto(book));
//        }
//        return bookDtos;

//        Alt + Enter ->  "Convert to Lambda" 또는 "Replace with method reference"

        return bookRepository.findAll()             // List<Book>를 반환
                .stream()                           // 리스트를 Stream으로 변환.
                .map(this::entityToDto)             // 각 Book 객체를 BookDto로 변환.
                .collect(Collectors.toList());      // 변환된 요소들을 List<BookDto>로 변환하여 반환
    }

    /**
     * 도서 단건 조회
     * 조회할 도서 코드를 기반으로 데이터를 조회하고 DTO로 변환하여 반환
     * @param bookCode 조회할 도서의 코드
     * @return 조회된 도서 정보 (DTO)
     */
    public BookDto getBook(Long bookCode) {
        Book book = bookRepository.findById(bookCode)
                // 값이 있으면 반환, 없으면 Supplier가 제공하는 예외를 던짐
                .orElseThrow(() -> new IllegalArgumentException("해당 도서를 찾을 수 없습니다."));
        return entityToDto(book); // 엔티티 -> DTO 변환 후 반환
    }

    /**
     * 도서 수정 메서드
     * 클라이언트에서 전달받은 데이터를 기반으로 기존 도서를 수정
     * @param bookDto 수정할 도서 정보
     */
    public void updateBook(BookDto bookDto) {
        // 필수 입력값 검증
        if (bookDto.getBookName() == null || bookDto.getBookName().isEmpty()) {
            throw new IllegalArgumentException("도서 제목은 필수 항목입니다.");
        }

        // 수정하려는 도서가 데이터베이스에 존재하는지 확인
        Book currentBook = bookRepository.findById(bookDto.getBookCode())
                // 값이 있으면 반환, 없으면 Supplier가 제공하는 예외를 던짐
                .orElseThrow(() -> new IllegalArgumentException("해당 도서를 찾을 수 없습니다."));

        // 전달받은 데이터를 기반으로 새로운 엔티티 생성
        Book updatedBook = Book.builder()
                .bookCode(currentBook.getBookCode())    // bookCode는 PK이므로 기존 값을 유지
                .bookName(bookDto.getBookName())
                .publisher(bookDto.getPublisher())
                .isbn(bookDto.getIsbn())
                .build();

        // save 메서드는 Primary Key(bookCode)가 존재하면 기존 데이터를 수정(UPDATE)한다.
        bookRepository.save(updatedBook);   // 수정된 도서 정보 반환
    }


    /**
     * 도서 삭제 메서드
     * 특정 도서 코드를 기준으로 데이터를 삭제
     * @param bookCode 삭제할 도서의 코드
     */
    public void deleteBook(Long bookCode) {
        bookRepository.deleteById(bookCode);
    }

}
