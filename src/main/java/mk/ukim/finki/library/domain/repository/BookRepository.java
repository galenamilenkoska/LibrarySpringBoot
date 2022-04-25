package mk.ukim.finki.library.domain.repository;

import mk.ukim.finki.library.domain.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
