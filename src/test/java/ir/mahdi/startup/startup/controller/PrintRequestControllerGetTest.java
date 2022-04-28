package ir.mahdi.startup.startup.controller;

import com.github.javafaker.Faker;
import ir.mahdi.startup.startup.model.entity.PrintRequest;
import ir.mahdi.startup.startup.repository.PrintRequestRepo;
import ir.mahdi.startup.startup.repository.custom.PrintRequestCustomRepImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrintRequestControllerGetTest {
    private static int printRequestCode;


    @MockBean
    private PrintRequestRepo printRequestRepo;

    @MockBean
    private PrintRequestCustomRepImpl printRequestCustomRep;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    public static void setup() {
        Faker faker = new Faker();
        printRequestCode = Integer.parseInt(faker.code().gtin8());
    }


    @Test
    public void when_callGetAll_then_get200HttpState() {
        Mockito.when(printRequestRepo.findAll(isA(Pageable.class))).thenReturn(Page.empty());
        int expected = 200;
        int actual = restTemplate.getForEntity("http://localhost:" + port + "/api/PrintRequest/getAll/0/10", String.class).getStatusCode().value();
        assertEquals(expected, actual);
    }


    @Test
    public void when_callGetByBranchCodeWithNotExisted_printRequest_then_get200HttpState() {
        Mockito.when(printRequestRepo.findByCode(any())).thenReturn(Optional.empty());
        int expected = 400;
        int actual = restTemplate.getForEntity("http://localhost:" + port + "/api/PrintRequest/getByCode/123" + printRequestCode, String.class).getStatusCode().value();
        assertEquals(expected, actual);
    }

    @Test
    public void when_callGetByBranchCodeWithExisted_printRequest_then_get200HttpState() {

        Mockito.when(printRequestRepo.findByCode(any())).thenReturn(Optional.of(new PrintRequest()));
        int expected = 200;
        int actual = restTemplate.getForEntity("http://localhost:" + port + "/api/PrintRequest/getByCode/123" + printRequestCode, String.class).getStatusCode().value();
        assertEquals(expected, actual);
    }

    @Test
    public void when_callGetAllIpAddressByBranchCode_then_get200HttpState() {
        Mockito.when(printRequestCustomRep.getAllIpAddressByBranchCode(any())).thenReturn(new ArrayList<>());
        int expected = 200;
        int actual = restTemplate.getForEntity("http://localhost:" + port + "/api/PrintRequest/getAllIpAddressByBranchCode/1234/", String.class).getStatusCode().value();
        assertEquals(expected, actual);
    }

}
