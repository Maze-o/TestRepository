package com.jcsg.test.book.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {

    private long bookCode;      // 책ID (Primary Key)
    private String bookName;    // 제목
    private String publisher;   // 출판사
    private String isbn;        // ISBN

}
