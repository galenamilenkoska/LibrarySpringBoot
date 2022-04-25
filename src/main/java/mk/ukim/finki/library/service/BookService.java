package mk.ukim.finki.library.service;

import mk.ukim.finki.library.domain.models.Book;
import mk.ukim.finki.library.domain.models.dto.BookDto;

import java.util.List;

public interface BookService {

    List<Book> listAll();

    Book findById(Long id);

    Book create(String name, String category, Long authorId, Integer availableCopies);

    Book create(BookDto bookDto);

    Book update(Long id,String name, String category, Long authorId, Integer availableCopies);

    Book update(Long id,BookDto bookDto);

    Book delete(Long id);

}
