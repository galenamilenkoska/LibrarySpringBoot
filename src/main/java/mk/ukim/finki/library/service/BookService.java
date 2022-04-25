package mk.ukim.finki.library.service;

import mk.ukim.finki.library.domain.models.Book;
import mk.ukim.finki.library.domain.models.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> listAll();

    Optional<Book> findById(Long id);

    Optional<Book> create(String name, String category, Long authorId, Integer availableCopies);

    Optional<Book> create(BookDto bookDto);

    Optional<Book> update(Long id,String name, String category, Long authorId, Integer availableCopies);

    Optional<Book> update(Long id,BookDto bookDto);

    Optional<Book> delete(Long id);

}
