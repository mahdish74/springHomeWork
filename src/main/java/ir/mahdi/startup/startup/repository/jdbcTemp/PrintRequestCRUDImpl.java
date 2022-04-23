package ir.mahdi.startup.startup.repository.jdbcTemp;

import ir.mahdi.startup.startup.model.entity.PrintRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrintRequestCRUDImpl{
    private final JdbcTemplate jdbcTemplate;

    public PrintRequestCRUDImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void addPreCardRequests(List<PrintRequest> printRequests) {
        printRequests.forEach(k -> jdbcTemplate.
                update("INSERT INTO t_PrintRequest (c_PersonalCode,c_BranchCode,c_CardPAN,c_IpAddress,c_code,C_ISSUEDATE) VALUES (?,?,?,?,?,?)", k.getPersonalCode(), k.getBranchCode(), k.getCardPAN(), k.getIpAddress(),k.getCode(),k.getIssueDate()));
    }


}
