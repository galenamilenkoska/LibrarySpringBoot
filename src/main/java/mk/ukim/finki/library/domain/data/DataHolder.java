package mk.ukim.finki.library.domain.data;

import lombok.Getter;
import mk.ukim.finki.library.service.AuthorService;
import mk.ukim.finki.library.service.BookService;
import mk.ukim.finki.library.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
//@Getter
public class DataHolder {

    CountryService countryService;
    AuthorService authorService;
    BookService bookService;

    public DataHolder(CountryService countryService, AuthorService authorService, BookService bookService) {
        this.countryService = countryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @PostConstruct
    public void init(){
        this.countryService.create("Macedonia","Europe");
        this.countryService.create("France","Europe");

        this.authorService.create("Slavko","Janevski",1L);
        this.authorService.create("Jules","Verne",2L);

        this.bookService.create("Shekerna prikazna","FANTASY",1L,5);
        this.bookService.create("Pat okolu svetot za 80dena","FANTASY",2L,2);
    }
}
