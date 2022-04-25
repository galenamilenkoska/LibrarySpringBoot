package mk.ukim.finki.library.service.impl;

import mk.ukim.finki.library.domain.exceptions.AuthorDoesNotExistException;
import mk.ukim.finki.library.domain.models.Author;
import mk.ukim.finki.library.domain.models.Country;
import mk.ukim.finki.library.domain.repository.AuthorRepository;
import mk.ukim.finki.library.domain.repository.CountryRepository;
import mk.ukim.finki.library.service.AuthorService;
import mk.ukim.finki.library.service.CountryService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    AuthorRepository authorRepository;
    CountryService countryService;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService=countryService;
    }

    @Override
    public Author findById(Long id) {
        return this.authorRepository.findById(id).orElseThrow(AuthorDoesNotExistException::new);
    }

    @Override
    public Author create(String name, String surname, Long countryId) {
        Country country=this.countryService.findById(countryId);
        return this.authorRepository.save(new Author(name,surname,country));
    }
}
