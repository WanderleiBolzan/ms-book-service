package br.com.wanderlei.proxy;

import br.com.wanderlei.dto.Exchange;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name="exchange-service", url="localhost:8000")
public interface ExchangeProxy {

    @GetMapping (value="exchange-service/{amount}/{from}/{to}")
    public Exchange getEchange(
            @PathVariable ("amount") Double amount,
            @PathVariable("from") String from,
            @PathVariable("to") String to);

}
