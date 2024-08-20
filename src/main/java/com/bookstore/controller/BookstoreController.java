package com.bookstore.controller;

import com.bookstore.model.Book;
import com.bookstore.repository.BookRepository;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/book")
public class BookstoreController {
    @Autowired
    BookRepository bookRepository;

    @PostMapping
    public String createBook(@Valid @RequestBody Book book) {
        bookRepository.save(book);

        return "Created Successfully";
    }

    @PutMapping
    public String updateBook(@Valid @RequestBody Book book) throws BadRequestException {

        try {
            Optional<Book> updateBook = bookRepository.findById(book.getBookId());
            if (updateBook.isPresent()) {
                bookRepository.save(book);
                return "Updated Successfully";
            } else {
                throw new BadRequestException("Invalid Book Reference");
            }
        } catch (NullPointerException e) {
            throw new BadRequestException("InvalidBookReference");
        }
    }

    @GetMapping
    public List<Book> getBook(@RequestParam(value = "title", required = false) String title,
                              @RequestParam(value = "birthday", required = false) String birthday,
                              @RequestParam(value = "name", required = false) String name) {
        LocalDate date = null;
        if (birthday != null)
            date = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return bookRepository.findBytitleAndBirthdayAndName(title, name, date);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable UUID id) {
        bookRepository.deleteById(id);
        return "Deleted Successfully";
    }
}
