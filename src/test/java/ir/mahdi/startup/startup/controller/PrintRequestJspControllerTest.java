package ir.mahdi.startup.startup.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrintRequestJspControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void when_call_getForm_then_getPageInfo() {
        int expected = 200;
        int actual = restTemplate.getForEntity("http://localhost:" + port + "/printRequest/getForm", String.class).getStatusCode().value();
        assertEquals(expected, actual);
    }

}
