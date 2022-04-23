package mapper;



import ir.mahdi.startup.startup.dto.out.PrintRequestRes;
import ir.mahdi.startup.startup.dto.in.PrintRequestReq;
import ir.mahdi.startup.startup.mapper.PrintCardRequestMapper;
import ir.mahdi.startup.startup.model.entity.PrintRequest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PrintCardRequestMapperTest {

    @Test
    public void when_give_PrintRequestEntityWithAllProperty_then_getPrintRequestResWithAllProperty() {
        PrintRequestRes expected = getValidPrintRequestRes();
        PrintRequest validPrintRequest = getValidPrintRequest();
        PrintRequestRes actual = PrintCardRequestMapper.mapPrintRequestToPrintRequestRes(validPrintRequest);
        assertEquals(expected, actual);
    }


    @Test
    @Disabled
    public void when_give_PrintRequestReqWithAllProperty_then_getPrintRequestEntityWithCorrespondingProperty() {
        PrintRequest expected = getValidPrintRequest();
        PrintRequestReq validPrintRequestReq = getValidPrintRequestReq();
        PrintRequest actual = PrintCardRequestMapper.mapPrintRequestReqToPrintRequest(validPrintRequestReq);
        assertEquals(expected.getBranchCode(), actual.getBranchCode());
        assertEquals(expected.getCardPAN(), actual.getCardPAN());
        assertEquals(expected.getIpAddress(), actual.getIpAddress());
    }

    private PrintRequestRes getValidPrintRequestRes() {
        Calendar myCalendar = new GregorianCalendar(1995, Calendar.APRIL, 2);
        Date issueDate = myCalendar.getTime();
        return new PrintRequestRes()
                .setIpAddress("10.20.152.113")
                .setCardPAN("5022291234567891")
                .setBranchCode("123")
                .setIssueDate(issueDate)
                .setPersonalCode("1234");
    }

    private PrintRequest getValidPrintRequest() {
        Calendar myCalendar = new GregorianCalendar(1995, Calendar.APRIL, 2);
        Date issueDate = myCalendar.getTime();
        return new PrintRequest()
                .setIpAddress("10.20.152.113")
                .setCardPAN("5022291234567891")
                .setBranchCode("123")
                .setIssueDate(issueDate)
                .setPersonalCode("1234");
    }

    private PrintRequestReq getValidPrintRequestReq() {
        Calendar myCalendar = new GregorianCalendar(1995, Calendar.APRIL, 2);
        Date issueDate = myCalendar.getTime();
        return new PrintRequestReq()
                .setIpAddress("10.20.152.113")
                .setCardPAN("5022291234567891")
                .setBranchCode("123")
                .setIssueDate(new Date())
                .setPersonalCode("1234");
    }


}