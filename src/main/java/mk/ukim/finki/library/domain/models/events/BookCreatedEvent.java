package mk.ukim.finki.library.domain.models.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;
import java.time.LocalDateTime;

@Getter
public class BookCreatedEvent extends ApplicationEvent {

    private LocalDateTime when;


    public BookCreatedEvent(Object source) {
        super(source);
    }

    public BookCreatedEvent(Object source, Clock clock) {
        super(source, clock);
    }
}
