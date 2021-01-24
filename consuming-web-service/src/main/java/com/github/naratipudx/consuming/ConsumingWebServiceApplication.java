package com.github.naratipudx.consuming;

import com.github.naratipudx.consuming.wsdl.GetCountryRequest;
import com.github.naratipudx.consuming.wsdl.GetCountryResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConsumingWebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumingWebServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner lookup(ClientConnector connector) {
        return args -> {
            String country = "Spain";

            if (args.length > 0) {
                country = args[0];
            }

            GetCountryRequest countryRequest = new GetCountryRequest();
            countryRequest.setName(country);

//            GetCountryResponse countryResponse = (GetCountryResponse) connector.callWebService(
//                "http://localhost:8080/ws/countries", countryRequest);

            GetCountryResponse countryResponse = (GetCountryResponse) connector.callWebService(
                "http://localhost:8080/ws/countries", countryRequest,
                "http://github.com/naratipudx/webservice/GetCountryRequest");

            System.err.println("Country: " + countryResponse.getCountry().getName());
            System.err.println("Capital: " + countryResponse.getCountry().getCapital());
            System.err.println("Currency: " + countryResponse.getCountry().getCurrency());
            System.err.println("Population: " + countryResponse.getCountry().getPopulation());
        };
    }
}
