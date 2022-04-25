package mk.ukim.finki.library.service.impl;

import mk.ukim.finki.library.domain.exceptions.BookDoesNotExistException;
import mk.ukim.finki.library.domain.models.Author;
import mk.ukim.finki.library.domain.models.Book;
import mk.ukim.finki.library.domain.models.Category;
import mk.ukim.finki.library.domain.models.dto.BookDto;
import mk.ukim.finki.library.domain.models.events.BookCreatedEvent;
import mk.ukim.finki.library.domain.repository.BookRepository;
import mk.ukim.finki.library.service.AuthorService;
import mk.ukim.finki.library.service.BookService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    final BookRepository bookRepository;
    final AuthorService authorService;
    final ApplicationEventPublisher applicationEventPublisher;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, ApplicationEventPublisher applicationEventPublisher) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.applicationEventPublisher = applicationEventPublisher;
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
    @Transactional
    public Book create(String name, String category, Long authorId, Integer availableCopies) {
        Author author=this.authorService.findById(authorId);
        Book book=new Book(name, Category.valueOf(category),author,availableCopies);
        this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book));
        return this.bookRepository.save(book);
    }

    @Override
    public Book create(BookDto bookDto) {
        Author author=this.authorService.findById(bookDto.getAuthor());
        Book book=new Book(bookDto.getName(), Category.valueOf(bookDto.getCategory()),author,bookDto.getAvailableCopies());
        this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book));
        return this.bookRepository.save(book);
    }

    @Override
    @Transactional
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
    public Book update(Long id,BookDto bookDto) {
        Book book=this.findById(id);
        book.setName(bookDto.getName());
        Author author=this.authorService.findById(bookDto.getAuthor());
        book.setAuthor(author);
        book.setCategory(Category.valueOf(bookDto.getCategory()));
        book.setAvailableCopies(bookDto.getAvailableCopies());
        return this.bookRepository.save(book);
    }

    @Override
    public Book delete(Long id) {
        Book book=this.findById(id);
        this.bookRepository.delete(book);
        return book;
    }
}
