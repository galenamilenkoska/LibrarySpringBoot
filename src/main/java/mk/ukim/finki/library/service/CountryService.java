package mk.ukim.finki.library.service;

import mk.ukim.finki.library.domain.models.Author;
import mk.ukim.finki.library.domain.models.Country;

public interface CountryService {

    Country findById(Long id);
    Country create(String name, String continent);
}
