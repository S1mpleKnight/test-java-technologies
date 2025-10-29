package ivan.zelezinski.lab.service.receiver;

import ivan.zelezinski.lab.mapper.book.BookDto;
import ivan.zelezinski.lab.service.book.BookService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Setter
@RequiredArgsConstructor
@Slf4j
public class RabbitReceiver {

    private final BookService bookService;

    @RabbitListener(queues = {"firstBookQueue", "secondBookQueue"})
    public void receiveFirstQueue(BookDto book) {
        log.info("Book message received, book name is {}", book.getName());
        bookService.create(book);
    }
}
