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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
    public Optional<Book> findById(Long id) {
        return Optional.of(this.bookRepository.findById(id).orElseThrow(BookDoesNotExistException::new));
    }

    @Override
    @Transactional
    public Optional<Book> create(String name, String category, Long authorId, Integer availableCopies) {
        Author author=this.authorService.findById(authorId);
        Book book=new Book(name, Category.valueOf(category),author,availableCopies);
        this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book));
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> create(BookDto bookDto) {
        Author author=this.authorService.findById(bookDto.getAuthor());
        Book book=new Book(bookDto.getName(), Category.valueOf(bookDto.getCategory()),author,bookDto.getAvailableCopies());
        this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book));
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    @Transactional
    public Optional<Book> update(Long id, String name, String category, Long authorId, Integer availableCopies) {
        Book book=this.bookRepository.findById(id).orElseThrow();
        book.setName(name);
        Author author=this.authorService.findById(authorId);
        book.setAuthor(author);
        book.setCategory(Category.valueOf(category));
        book.setAvailableCopies(availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> update(Long id,BookDto bookDto) {
        Book book=this.findById(id).orElseThrow();
        book.setName(bookDto.getName());
        Author author=this.authorService.findById(bookDto.getAuthor());
        book.setAuthor(author);
        book.setCategory(Category.valueOf(bookDto.getCategory()));
        book.setAvailableCopies(bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> delete(Long id) {
        Book book=this.findById(id).orElseThrow();
        this.bookRepository.delete(book);
        return Optional.of(book);
    }

    @Override
    public Page<Book> findAllWithPagination(Pageable pageable) {
        return this.bookRepository.findAll(pageable);
    }

}
