package mk.ukim.finki.library.domain.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.library.domain.models.Author;
import mk.ukim.finki.library.domain.models.Category;

import javax.persistence.*;


@Data
@NoArgsConstructor
public class BookDto {


    String name;

    String category;

    Long author;

    Integer availableCopies;


    public BookDto(String name, String category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}