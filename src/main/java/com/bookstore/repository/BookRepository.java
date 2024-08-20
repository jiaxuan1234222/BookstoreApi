package com.bookstore.repository;

import com.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


public interface BookRepository extends JpaRepository<Book, UUID>, JpaSpecificationExecutor<Book> {
    @Query("SELECT DISTINCT b FROM Book b JOIN b.authors a WHERE b.title = :title OR (a.name = :name AND a.birthday = :birthday)")
    List<Book> findBytitleAndBirthdayAndName(@Param("title") String title, @Param("name") String name, @Param("birthday") LocalDate birthday);

    void deleteById(UUID id);
}
