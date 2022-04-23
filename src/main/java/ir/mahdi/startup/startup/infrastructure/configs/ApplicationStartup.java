package ir.mahdi.startup.startup.infrastructure.configs;

import com.github.javafaker.Faker;
import ir.mahdi.startup.startup.infrastructure.utils.Application;
import ir.mahdi.startup.startup.model.entity.PrintRequest;
import ir.mahdi.startup.startup.repository.jdbcTemp.PrintRequestCRUDImpl;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
    private final PrintRequestCRUDImpl printRequestCRUD;

    public ApplicationStartup(PrintRequestCRUDImpl printRequestCRUD) {
        this.printRequestCRUD = printRequestCRUD;
    }


    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        List<PrintRequest> printRequests = getFakePrintCardRequests();
      //  printRequestCRUD.addPreCardRequests(printRequests);

    }

    private List<PrintRequest> getFakePrintCardRequests() {
        List<PrintRequest> printRequests = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            PrintRequest printRequest = getFakePrintRequest();
            printRequests.add(printRequest);
        }
        return printRequests;
    }

    private PrintRequest getFakePrintRequest() {
        Faker faker = new Faker();

        return new PrintRequest()
                .setBranchCode(faker.code().gtin8())
                .setPersonalCode(faker.code().gtin8())
                .setCardPAN(faker.code().gtin8()+faker.code().gtin8())
                .setIpAddress(faker.internet().ipV4Address())
                .setCode(new Random().nextLong())
                .setIssueDate(new Date());

    }
}
