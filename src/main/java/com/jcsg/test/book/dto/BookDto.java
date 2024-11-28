package com.jcsg.test.book.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "tbl_book")
public class BookDto {

    @Id
    private long bookCode;

    private String bookName;
    private String publisher;
    private String isbn;



}
