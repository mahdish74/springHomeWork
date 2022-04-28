package ir.mahdi.startup.startup.controller;

import ir.mahdi.startup.startup.model.entity.PrintRequest;
import ir.mahdi.startup.startup.repository.PrintRequestRepo;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrintRequestControllerInsertTest {


    @MockBean
    private PrintRequestRepo printRequestRepo;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void when_give_EmptyPersonalCode_then_getRelatedMessageAboutPersonalCode() {
        JSONObject printCardRequest = createPrintCardRequestJsonObject("personalCode");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(printCardRequest.toString(), headers);

        Mockito.when(printRequestRepo.save(isA(PrintRequest.class))).thenReturn(new PrintRequest());
        String expected = "{\"personalCode\":\"پرسنال کد اجباری است.\"}";
        String actual = restTemplate.postForObject("http://localhost:" + port + "/api/PrintRequest/create", request, String.class);
        assertEquals(expected, actual);
    }

    @Test
    public void when_give_EmptyBranchCode_then_getRelatedMessageAboutBranchCode() {
        JSONObject printCardRequest = createPrintCardRequestJsonObject("branchCode");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(printCardRequest.toString(), headers);

        Mockito.when(printRequestRepo.save(isA(PrintRequest.class))).thenReturn(new PrintRequest());
        String expected = "{\"branchCode\":\"کد شعبه اجباری است.\"}";
        String actual = restTemplate.postForObject("http://localhost:" + port + "/api/PrintRequest/create", request, String.class);
        assertEquals(expected, actual);
    }

    @Test
    public void when_give_EmptyCardPAN_then_getRelatedMessageAboutCardPAN() {
        JSONObject printCardRequest = createPrintCardRequestJsonObject("cardPAN");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(printCardRequest.toString(), headers);

        Mockito.when(printRequestRepo.save(isA(PrintRequest.class))).thenReturn(new PrintRequest());
        String expected = "{\"cardPAN\":\"شماره کارت اجباری است.\"}";
        String actual = restTemplate.postForObject("http://localhost:" + port + "/api/PrintRequest/create", request, String.class);
        assertEquals(expected, actual);
    }

    @Test
    public void when_give_EmptyIpAddress_then_getRelatedMessageAboutIpAddress() {
        JSONObject printCardRequest = createPrintCardRequestJsonObject("ipAddress");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(printCardRequest.toString(), headers);

        Mockito.when(printRequestRepo.save(isA(PrintRequest.class))).thenReturn(new PrintRequest());
        String expected = "{\"ipAddress\":\"ادرس ای پی اجباری است.\"}";
        String actual = restTemplate.postForObject("http://localhost:" + port + "/api/PrintRequest/create", request, String.class);
        assertEquals(expected, actual);
    }

    @Test
    public void when_give_EmptyIssueDate_then_getRelatedMessageAboutIssueDate() {
        JSONObject printCardRequest = createPrintCardRequestJsonObject("issueDate");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(printCardRequest.toString(), headers);

        Mockito.when(printRequestRepo.save(isA(PrintRequest.class))).thenReturn(new PrintRequest());
        String expected = "{\"issueDate\":\"تاریخ اجباری است.\"}";
        String actual = restTemplate.postForObject("http://localhost:" + port + "/api/PrintRequest/create", request, String.class);
        assertEquals(expected, actual);
    }


    @Test
    public void when_give_InValidPersonalCode_then_getRelatedMessageAboutPersonalCode() {
        JSONObject printCardRequest = createPrintCardRequestJsonObject();
        printCardRequest.put("personalCode", "12");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(printCardRequest.toString(), headers);

        Mockito.when(printRequestRepo.save(isA(PrintRequest.class))).thenReturn(new PrintRequest());
        String expected = "{\"personalCode\":\"شماره پرسنلی معتبر نیست.\"}";
        String actual = restTemplate.postForObject("http://localhost:" + port + "/api/PrintRequest/create", request, String.class);
        assertEquals(expected, actual);
    }

    @Test
    public void when_give_InValidBranchCode_then_getRelatedMessageAboutBranchCode() {
        JSONObject printCardRequest = createPrintCardRequestJsonObject();
        printCardRequest.put("branchCode", "1");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(printCardRequest.toString(), headers);

        Mockito.when(printRequestRepo.save(isA(PrintRequest.class))).thenReturn(new PrintRequest());
        String expected = "{\"branchCode\":\"شماره شعبه معتبر نیست.\"}";
        String actual = restTemplate.postForObject("http://localhost:" + port + "/api/PrintRequest/create", request, String.class);
        assertEquals(expected, actual);
    }

    @Test
    public void when_give_InvalidCardPAN_then_getRelatedMessageAboutCardPAN() {
        JSONObject printCardRequest = createPrintCardRequestJsonObject("cardPAN");

        printCardRequest.put("cardPAN", "1");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(printCardRequest.toString(), headers);

        Mockito.when(printRequestRepo.save(isA(PrintRequest.class))).thenReturn(new PrintRequest());
        String expected = "{\"cardPAN\":\"شماره کارت معتبر نیست.\"}";
        String actual = restTemplate.postForObject("http://localhost:" + port + "/api/PrintRequest/create", request, String.class);
        assertEquals(expected, actual);
    }


    @Test
    public void when_give_InvalidIpAddress_then_getRelatedMessageAboutIpAddress() {
        JSONObject printCardRequest = createPrintCardRequestJsonObject("ipAddress");

        printCardRequest.put("ipAddress", "15321");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(printCardRequest.toString(), headers);

        Mockito.when(printRequestRepo.save(isA(PrintRequest.class))).thenReturn(new PrintRequest());
        String expected = "{\"ipAddress\":\"ادرس ای پی معتبر نیست.\"}";
        String actual = restTemplate.postForObject("http://localhost:" + port + "/api/PrintRequest/create", request, String.class);
        assertEquals(expected, actual);
    }

    private JSONObject createPrintCardRequestJsonObject(String... mustRemoveKey) {
        JSONObject printCardRequest = new JSONObject();

        printCardRequest.put("personalCode", "1324756");
        printCardRequest.put("branchCode", "13246");
        printCardRequest.put("cardPAN", "stringstringstri");
        printCardRequest.put("ipAddress", "452.5.877.405");
        printCardRequest.put("issueDate", "20-04-2022");

        for (String key : mustRemoveKey) {
            printCardRequest.remove(key);
        }
        return printCardRequest;
    }

}
