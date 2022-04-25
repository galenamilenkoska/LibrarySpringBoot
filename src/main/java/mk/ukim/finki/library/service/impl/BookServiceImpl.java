package mk.ukim.finki.library.service.impl;

import mk.ukim.finki.library.domain.exceptions.BookDoesNotExistException;
import mk.ukim.finki.library.domain.models.Author;
import mk.ukim.finki.library.domain.models.Book;
import mk.ukim.finki.library.domain.models.Category;
import mk.ukim.finki.library.domain.repository.AuthorRepository;
import mk.ukim.finki.library.domain.repository.BookRepository;
import mk.ukim.finki.library.service.AuthorService;
import mk.ukim.finki.library.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;
    AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return this.bookRepository.findById(id).orElseThrow(BookDoesNotExistException::new);
    }

    @Override
    public Book create(String name, String category, Long authorId, Integer availableCopies) {
        Author author=this.authorService.findById(authorId);
        return this.bookRepository.save(new Book(name, Category.valueOf(category),author,availableCopies));
    }

    @Override
    public Book update(Long id, String name, String category, Long authorId, Integer availableCopies) {
        Book book=this.findById(id);
        book.setName(name);
        Author author=this.authorService.findById(authorId);
        book.setAuthor(author);
        book.setCategory(Category.valueOf(category));
        book.setAvailableCopies(availableCopies);
        return this.bookRepository.save(book);
    }

    @Override
    public Book delete(Long id) {
        Book book=this.findById(id);
        this.bookRepository.delete(book);
        return book;
    }
}
