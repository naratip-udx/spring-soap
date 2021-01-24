package com.github.naratipudx.consuming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.naratipudx.consuming.wsdl.Currency;
import com.github.naratipudx.consuming.wsdl.GetCountryRequest;
import com.github.naratipudx.consuming.wsdl.GetCountryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

// Ensure Producing web service is running before executing this test
@SpringBootTest
@ContextConfiguration(classes = ClientConfig.class, loader = AnnotationConfigContextLoader.class)
class CountryClientTest {

    @Autowired
    ClientConnector connector;

    final String COUNTRY_URL = "http://localhost:8080/ws/countries";
    GetCountryRequest request;

    @BeforeEach
    void setup() {
        request = new GetCountryRequest();
    }

    @Test
    void whenCountryIsSpain_thenCapitalIsMadrid() {
        request.setName("Spain");
        GetCountryResponse response = (GetCountryResponse) connector.callWebService(COUNTRY_URL, request);

        assertEquals("Madrid", response.getCountry().getCapital());
    }

    @Test
    void whenCountryIsPoland_thenCurrencyIsPLN() {
        request.setName("Poland");
        GetCountryResponse response = (GetCountryResponse) connector.callWebService(COUNTRY_URL, request);

        assertEquals(Currency.PLN, response.getCountry().getCurrency());
    }
}
