package com.example.bookservice;


import com.example.Author;
import com.example.BookAuthorServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class AuthorClientService {
    @GrpcClient("author-service")
    private BookAuthorServiceGrpc.BookAuthorServiceBlockingStub authorService;


    public Author getAuthor(Long id) {
        return authorService.getAuthor(Author.newBuilder().setId(id).build());
    }
}
