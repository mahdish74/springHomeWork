package ir.mahdi.startup.startup.controller;

import ir.mahdi.startup.startup.model.entity.PrintRequest;
import ir.mahdi.startup.startup.repository.PrintRequestRepo;
import ir.mahdi.startup.startup.service.PrintRequestService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrintRequestControllerTest {


    @MockBean
    private PrintRequestRepo printRequestRepo;

//    @MockBean
//    private PrintRequestService service;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    private void myTest(){
        Mockito.when(printRequestRepo.findByCode(any())).thenReturn(Optional.of(new PrintRequest()));
        restTemplate.getForObject("http://localhost:" + port + "/message", String.class);

    }



}
