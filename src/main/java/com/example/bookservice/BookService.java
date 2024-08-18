package com.example.bookservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;

    public Book createBook(CreateBookRequest body) {
        Book book = new Book();
        book.setTitle(body.getTitle());
        book.setAuthorId(body.getAuthorId());
        book.setGenre(body.getGenre());
        return repository.save(book);
    }

    public List<Book> getBooks() {
        return repository.findAll();
    }

    public Book getBook(Long id) {
        return repository.findById(id).orElseThrow();
    }


}
