package com.example.bookservice;

import com.example.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService service;
    private final AuthorClientService authorClientService;

    @PostMapping
    public Book createBook(@RequestBody CreateBookRequest body) {
        return service.createBook(body);
    }

    @GetMapping
    public List<Book> getBooks() {
        return service.getBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable Long id) {
        Book book = service.getBook(id);
        Author author = authorClientService.getAuthor(book.getAuthorId());
        AuthorResponse authorResponse = AuthorResponse.builder()
                .id(author.getId())
                .name(author.getName())
                .email(author.getEmail())
                .bio(author.getBio())
                .build();

        BookResponse bookResponse = BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .genre(book.getGenre())
                .author(authorResponse)
                .build();

        return ResponseEntity.ok(bookResponse);
    }

}
