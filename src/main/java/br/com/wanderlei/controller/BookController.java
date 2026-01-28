package br.com.wanderlei.controller;

import br.com.wanderlei.BookRepository;
import br.com.wanderlei.dto.Exchange;
import br.com.wanderlei.environment.InstanceInformationService;
import br.com.wanderlei.model.Book;
import br.com.wanderlei.proxy.ExchangeProxy;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name="Book Endpoint")
@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    private InstanceInformationService informationService;

    @Autowired
    private BookRepository repository;

    @Autowired
    private ExchangeProxy proxy;

    //http://localhost:8100/book-service/1/BRL
    @Operation(summary = "Find a specific book by ID")
    @GetMapping(value="/{id}/{currency}", produces = MediaType.APPLICATION_JSON_VALUE )
    public Book findBook(
            @PathVariable("id") Long id,
            @PathVariable("currency") String currency
    ) {
        String port = informationService.retrieverServerPort ();

        var book = repository.findById(id).orElseThrow();


        Exchange exchange = proxy.getEchange (book.getPrice (), "USD", currency);

        book.setEnvironment (port + " FEIGN ");
        book.setPrice (exchange.getConvertedValue ());
        book.setCurrency (currency);

        return book;
    }
/*
    //http://localhost:8100/book-service/1/BRL
    @GetMapping(value="/{id}/{currency}", produces = MediaType.APPLICATION_JSON_VALUE )
    public Book findBook(
            @PathVariable("id") Long id,
            @PathVariable("currency") String currency
    ) {
        String port = informationService.retrieverServerPort ();

        var book = repository.findById(id).orElseThrow();

        HashMap<String, String> parans = new HashMap<>();
        parans.put ("amount", book.getPrice ().toString ());
        parans.put ("from", "USD");
        parans.put ("to", currency);

        var response = new RestTemplate()
                .getForEntity ("http://localhost:8000/exchange-service/{amount}/{from}/{to}", Exchange.class, parans);

        Exchange exchange = response.getBody ();

        book.setEnvironment (port);
        book.setPrice (exchange.getConvertedValue ());
        book.setCurrency (currency);

        return book;
    }

*/

    /*
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
*/
}
