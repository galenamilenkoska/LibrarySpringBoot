package mk.ukim.finki.library.service.impl;

import mk.ukim.finki.library.domain.exceptions.CountryNotFoundException;
import mk.ukim.finki.library.domain.models.Country;
import mk.ukim.finki.library.domain.repository.CountryRepository;
import mk.ukim.finki.library.service.CountryService;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {

    CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country findById(Long id) {
        return this.countryRepository.findById(id).orElseThrow(CountryNotFoundException::new);
    }

    @Override
    public Country create(String name, String continent) {
        Country country=new Country(name,continent);
        return this.countryRepository.save(country);
    }
}
