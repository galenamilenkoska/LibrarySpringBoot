package mk.ukim.finki.library.domain.repository;

import mk.ukim.finki.library.domain.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Long> {
}
