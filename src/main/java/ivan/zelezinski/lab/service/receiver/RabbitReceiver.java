package ivan.zelezinski.lab.service.receiver;

import ivan.zelezinski.lab.mapper.book.BookDto;
import ivan.zelezinski.lab.service.book.BookService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Setter
@RequiredArgsConstructor
public class RabbitReceiver {

    private final BookService bookService;

    @RabbitListener(queues = {"firstBookQueue", "secondBookQueue"})
    public void receiveFirstQueue(BookDto book) {
        bookService.create(book);
    }
}
