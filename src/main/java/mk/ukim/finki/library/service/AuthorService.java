package mk.ukim.finki.library.service;

import mk.ukim.finki.library.domain.models.Author;
import mk.ukim.finki.library.domain.models.Book;

public interface AuthorService {

    Author findById(Long id);
    Author create(String name, String surname, Long countryId);
}
