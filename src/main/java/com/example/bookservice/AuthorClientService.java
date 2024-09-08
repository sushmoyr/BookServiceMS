package com.example.bookservice;


import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import tech.utkorsho.grpc.Author;
import tech.utkorsho.grpc.BookAuthorServiceGrpc;

@Service
public class AuthorClientService {
    @GrpcClient("author-service")
    private BookAuthorServiceGrpc.BookAuthorServiceBlockingStub authorService;


    public Author getAuthor(Long id) {
        try {
            return authorService.getAuthor(Author.newBuilder().setId(id).build());

        } catch (Exception e) {
            throw new RuntimeException("Failed to get author", e);
        }
    }
}
