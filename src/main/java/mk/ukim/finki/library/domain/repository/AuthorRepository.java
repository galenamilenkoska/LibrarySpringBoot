package mk.ukim.finki.library.domain.repository;

import mk.ukim.finki.library.domain.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {
}
