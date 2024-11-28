package com.jcsg.test.book.domain.repository;

import com.jcsg.test.book.domain.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // JpaRepository는 기본적인 CRUD 메서드를 이미 제공하기 때문에 별도의 메서드를 추가하지 않아도 충분하다
}
