package mk.ukim.finki.library.service;

import mk.ukim.finki.library.domain.models.Book;

import java.util.List;

public interface BookService {

    List<Book> listAll();

    Book findById(Long id);

    Book create(String name, String category, Long authorId, Integer availableCopies);

    Book update(Long id,String name, String category, Long authorId, Integer availableCopies);

    Book delete(Long id);

}
