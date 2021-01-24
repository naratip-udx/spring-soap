package com.github.naratipudx.consuming;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.github.naratipudx.consuming.wsdl");
        return marshaller;
    }

    @Bean
    public ClientConnector clientConnector(Jaxb2Marshaller marshaller) {
        ClientConnector connector = new ClientConnector();
        connector.setDefaultUri("http://localhost:8080/ws");
        connector.setMarshaller(marshaller);
        connector.setUnmarshaller(marshaller);
        return connector;
    }
}
