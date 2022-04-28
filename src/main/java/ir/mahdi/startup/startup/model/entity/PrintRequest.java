package ir.mahdi.startup.startup.model.entity;

import ir.mahdi.startup.startup.model.embeddable.PrintRequestEmbeddedId;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@Table
@Audited
public class PrintRequest {

    @EmbeddedId
    private PrintRequestEmbeddedId embeddedId;

    @Column(nullable = false)
    @Size(min = 5, max = 10)
    private String personalCode;

    @Column(nullable = false, insertable = false, updatable = false)
    @Min(3)
    private String branchCode;

    @Column
    private String cardPAN;

    @Column(nullable = false, insertable = false, updatable = false)
    @Pattern(regexp = "^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$")
    private String ipAddress;

    @Column
    private Date issueDate;

    @Column
    private Long code;

    public PrintRequestEmbeddedId getEmbeddedId() {
        return embeddedId;
    }

    public PrintRequest setEmbeddedId(PrintRequestEmbeddedId embeddedId) {
        this.embeddedId = embeddedId;
        return this;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public PrintRequest setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
        return this;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public PrintRequest setBranchCode(String branchCode) {
        this.branchCode = branchCode;
        return this;
    }

    public String getCardPAN() {
        return cardPAN;
    }

    public PrintRequest setCardPAN(String cardPAN) {
        this.cardPAN = cardPAN;
        return this;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public PrintRequest setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public PrintRequest setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
        return this;
    }

    public Long getCode() {
        return code;
    }

    public PrintRequest setCode(Long code) {
        this.code = code;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PrintRequest that = (PrintRequest) o;
        return personalCode.equals(that.personalCode) &&
                branchCode.equals(that.branchCode) &&
                cardPAN.equals(that.cardPAN) &&
                ipAddress.equals(that.ipAddress) &&
                issueDate.equals(that.issueDate) &&
                code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalCode, branchCode, cardPAN, ipAddress, issueDate, code);
    }
}
