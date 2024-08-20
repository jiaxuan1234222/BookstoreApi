package com.bookstore.repository;

import com.bookstore.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class BookRepositoryTest {

    @Mock
    private BookRepository bookRepository;

    @Test
    public void testFindBytitleAndBirthdayAndName() {
        LocalDate birthday = LocalDate.of(1990, 1, 1);

        Book book = Book.builder()
                .bookId(UUID.randomUUID())
                .title("Sample Book")
                .build();

        List<Book> books = new ArrayList<>();
        books.add(book);

        Mockito.when(bookRepository.findBytitleAndBirthdayAndName("Sample Book", "John Doe", birthday))
                .thenReturn(books);

        List<Book> result = bookRepository.findBytitleAndBirthdayAndName("Sample Book", "John Doe", birthday);

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Sample Book", result.getFirst().getTitle());
    }
}
