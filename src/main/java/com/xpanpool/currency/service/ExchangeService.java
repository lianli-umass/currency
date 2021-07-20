package com.xpanpool.currency.service;

import com.xpanpool.currency.model.CurrencyPair;

public interface ExchangeService {
    public CurrencyPair getCurrencyPair(String base, String target);
}
