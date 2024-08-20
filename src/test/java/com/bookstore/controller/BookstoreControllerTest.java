package com.bookstore.controller;

import com.bookstore.model.Author;
import com.bookstore.model.Book;
import com.bookstore.repository.BookRepository;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

@SpringBootTest
public class BookstoreControllerTest {

    @Mock
    private static BookRepository bookRepository;

    @InjectMocks
    private BookstoreController bookstoreController = Mockito.spy(new BookstoreController());

    @Test
    void deleteBook() {
        String response = bookstoreController.deleteBook(UUID.randomUUID());
        Assertions.assertEquals(response, "Deleted Successfully");
    }

    @Test
    public void testGetBook_Success() {
        Book book = new Book();
        book.setTitle("Test Title");
        List<Book> books = Arrays.asList(book);

        Mockito.when(bookRepository.findBytitleAndBirthdayAndName("Test Title", "John Doe", LocalDate.parse("1980-01-01")))
                .thenReturn(books);

        List<Book> result = bookstoreController.getBook("Test Title", "1980-01-01", "John Doe");

        Mockito.verify(bookRepository).findBytitleAndBirthdayAndName("Test Title", "John Doe", LocalDate.parse("1980-01-01"));
        Assertions.assertEquals(books, result);
    }

    @Test
    void updateBook_Failure() {
        Assertions.assertThrows(BadRequestException.class,
                () -> bookstoreController.updateBook(Mockito.any(Book.class)));
    }

    @Test
    public void testUpdateBook_Success() throws BadRequestException {
        Book book = getMockBook();

        Mockito.when(bookRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(book));
        Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);

        String response = bookstoreController.updateBook(book);

        Mockito.verify(bookRepository).findById(book.getBookId());
        Mockito.verify(bookRepository).save(book);
        Assertions.assertEquals("Updated Successfully", response);
    }

    @Test
    public void testCreateBook_Success() {
        Book book = getMockBook();

        String response = bookstoreController.createBook(book);

        Mockito.verify(bookRepository).save(book);
        Assertions.assertEquals("Created Successfully", response);
    }


    private Book getMockBook() {
        Author author = Author.builder()
                .name("John Doe")
                .birthday(LocalDate.of(2020, 1, 8))
                .build();
        Set<Author> authorList = new HashSet<>();
        authorList.add(author);

        return Book.builder().bookId(UUID.fromString("26a82426-06f7-4f72-86f9-0478499c10cb"))
                .genre("Fiction")
                .price(12.22)
                .publicationYear(2023)
                .authors(authorList)
                .title("Sample Book").build();
    }

}