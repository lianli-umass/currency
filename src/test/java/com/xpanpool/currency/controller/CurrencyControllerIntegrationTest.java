package com.xpanpool.currency.controller;

import com.xpanpool.currency.model.CurrencyPair;
import com.xpanpool.currency.service.ExchangeGenerateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class CurrencyControllerIntegrationTest {
    private final String USD_CODE = "USD";
    private final String HKD_CODE = "HKD";
    private final Float RATE = 7.7671f;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExchangeGenerateService exchangeGenerateServiceMock;

    @Test
    void getCurrencyRate() throws Exception{

        CurrencyPair currencyPairMock = new CurrencyPair();
        currencyPairMock.setBase_code(USD_CODE);
        currencyPairMock.setTarget_code(HKD_CODE);
        currencyPairMock.setConversion_rate(RATE);
        when(exchangeGenerateServiceMock
                .getCurrencyPair(USD_CODE, HKD_CODE))
                .thenReturn(currencyPairMock);

        mockMvc.perform(get("/xpanpool/pair?base=USD&target=HKD"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.base_code").value(USD_CODE))
                .andExpect(jsonPath("$.target_code").value(HKD_CODE))
                .andExpect(jsonPath("$.conversion_rate").value(RATE));
        verify(exchangeGenerateServiceMock).getCurrencyPair(USD_CODE, HKD_CODE);

    }

    @Test
    void getCurrencyRateWithInvalidPrams() throws Exception {
        mockMvc.perform(get("/xpanpool/pair?base=USD&tar"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}