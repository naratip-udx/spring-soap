package com.github.naratipudx.webservice;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ProducingWebServiceApplicationTests {

    @LocalServerPort
    int port;

    final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

    @BeforeEach
    void setup() throws Exception {
        marshaller.setPackagesToScan(ClassUtils.getPackageName(GetCountryRequest.class));
        marshaller.afterPropertiesSet();
    }

    @Test
    void whenSendRequest_thenResponseIsNotNull() {
        WebServiceTemplate ws = new WebServiceTemplate(marshaller);

        GetCountryRequest request = new GetCountryRequest();
        request.setName("Spain");

        assertNotNull(ws.marshalSendAndReceive("http://localhost:" + port + "/ws", request));
    }
}
