package ir.mahdi.startup.startup.service;

import com.github.javafaker.Faker;
import ir.mahdi.startup.startup.dto.in.PrintRequestReq;
import ir.mahdi.startup.startup.dto.out.PrintRequestRes;
import ir.mahdi.startup.startup.exception.IssueDateNotValid;
import ir.mahdi.startup.startup.exception.PrintRequestAlreadyExists;
import ir.mahdi.startup.startup.model.entity.PrintRequest;
import ir.mahdi.startup.startup.repository.PrintRequestRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;

@ExtendWith(MockitoExtension.class)
public class PrintRequestServiceTest {
    private static String personalCode;
    private static String branchCode;
    private static String cardPAN;
    private static String ipAddress;
    private static Date issueDate;

    @BeforeAll
    public static void setup() {
        Faker faker = new Faker();
        personalCode = faker.code().gtin8();
        branchCode = faker.code().gtin8();
        cardPAN = "50222910" + faker.code().gtin8();
        ipAddress = faker.internet().ipV4Address();
        issueDate = new Date();
    }

    @Mock
    private PrintRequestRepo printRequestRepo;

    @InjectMocks
    private PrintRequestService printRequestService;

    @Test
    @DisplayName("successful scenario")
    public void when_give_validPrintRequestToInsertPrintRequest_Then_getSuccessfulResultWithCompatiblePrintRequestRes() {
        Mockito.when(printRequestRepo.save(isA(PrintRequest.class))).thenReturn(createValidPrintRequest());
        PrintRequestReq validPrintRequestReq = createValidPrintRequestReq();
        PrintRequestRes expected = createValidPrintRequestRes();
        PrintRequestRes actual = printRequestService.insertPrintRequest(validPrintRequestReq);
        assertEquals(expected.getPersonalCode(), actual.getPersonalCode());
        assertEquals(expected.getBranchCode(), actual.getBranchCode());
        assertEquals(expected.getCardPAN(), actual.getCardPAN());
        assertEquals(expected.getIpAddress(), actual.getIpAddress());
    }

    @Test
    public void when_give_ExistedPrintRequestToInsertPrintRequest_Then_throwPrintRequestAlreadyExists() {
        Mockito.when(printRequestRepo.existsPrintRequestByBranchCodeAndIpAddress(any(), any())).thenReturn(true);
        PrintRequestReq validPrintRequestReq = createValidPrintRequestReq();
        assertThrows(PrintRequestAlreadyExists.class, () -> printRequestService.insertPrintRequest(validPrintRequestReq));
    }


    @Test
    public void when_give_InvalidPrintRequestWithOutIssueDateToInsertPrintRequest_Then_throwPrintRequestAlreadyExists() {
        Mockito.when(printRequestRepo.existsPrintRequestByBranchCodeAndIpAddress(any(), any())).thenReturn(false);
        PrintRequestReq validPrintRequestReq = createValidPrintRequestReq();
        validPrintRequestReq.setIssueDate(null);
        assertThrows(IssueDateNotValid.class, () -> printRequestService.insertPrintRequest(validPrintRequestReq));
    }



    private PrintRequest createValidPrintRequest() {
        return new PrintRequest().setPersonalCode(personalCode).setBranchCode(branchCode).setCardPAN(cardPAN).setIpAddress(ipAddress).setIssueDate(issueDate);
    }

    private PrintRequestReq createValidPrintRequestReq() {
        return new PrintRequestReq().setPersonalCode(personalCode).setBranchCode(branchCode).setCardPAN(cardPAN).setIpAddress(ipAddress).setIssueDate(issueDate);
    }

    private PrintRequestRes createValidPrintRequestRes() {
        return new PrintRequestRes().setPersonalCode(personalCode).setBranchCode(branchCode).setCardPAN(cardPAN).setIpAddress(ipAddress).setIssueDate(issueDate);
    }

}
