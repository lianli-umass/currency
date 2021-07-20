package com.xpanpool.currency.controller;

import com.xpanpool.currency.model.CurrencyPair;
import com.xpanpool.currency.service.ExchangeGenerateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Rest Controller specifies the RESTful endpoint for the currency rate service.
 * The controller also maps method to the RESTful action specified.
 */

@RestController
@RequestMapping("/xpanpool")
public class CurrencyController {

    private ExchangeGenerateService exchangeGenerateService;

    public CurrencyController(ExchangeGenerateService exchangeGenerateService) {
        this.exchangeGenerateService = exchangeGenerateService;
    }

    /**
     * To get the currency rate from base to target
     * @param base: the base code of a country
     * @param target: the target code of a country
     * @return: CurrencyPair Objects specifying "base_code", "target_code", conversion_rate, "result", "error_type"
     */

    @GetMapping("/pair")
    public CurrencyPair getCurrencyRate(@RequestParam String base, @RequestParam String target) {
        return exchangeGenerateService.getCurrencyPair(base, target);
    }
}
