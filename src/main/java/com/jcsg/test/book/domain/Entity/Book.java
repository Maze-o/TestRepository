package com.jcsg.test.book.domain.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 접근 제어 레벨을 PROCTECTED 설정해 무분별한 객체 생성에 대해 한번 더 체크해줌
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_code")
    private long bookCode;      // 책ID (Primary Key)
    @Column(name = "book_name")
    private String bookName;    // 제목
    @Column(name = "publisher")
    private String publisher;   // 출판사
    @Column(name = "isbn")
    private String isbn;        // ISBN

    // 생성자에 따로 @Builder 적용
    @Builder
    public Book(long bookCode, String bookName, String publisher, String isbn) {
        this.bookCode = bookCode;
        this.bookName = bookName;
        this.publisher = publisher;
        this.isbn = isbn;
    }
}
