package com.bookstore;

import com.bookstore.controller.BookstoreController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BookstoreApplicationTests {

    @Autowired
    private BookstoreController bookstoreController;

    @Test
    void contextLoads() {
        assertNotNull(bookstoreController);
    }

}
