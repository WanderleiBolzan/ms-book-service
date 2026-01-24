package br.com.wanderlei.controller;

import br.com.wanderlei.BookRepository;
import br.com.wanderlei.environment.InstanceInformationService;
import br.com.wanderlei.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    private InstanceInformationService informationService;

    @Autowired
    private BookRepository repository;

    //http://localhost:8100/book-service/1/BRL
    @GetMapping(value="/{id}/{currency}", produces = MediaType.APPLICATION_JSON_VALUE )
    public Book findBook(
            @PathVariable("id") Long id,
            @PathVariable("currency") String currency
    ) {
        String port = informationService.retrieverServerPort ();
        var book = repository.findById(id).orElseThrow();

        book.setEnvironment (port);
        book.setCurrency (currency);

        return book;
    }

}
