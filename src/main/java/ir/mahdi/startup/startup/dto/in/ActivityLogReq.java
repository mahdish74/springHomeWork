package ir.mahdi.startup.startup.dto.in;

import javax.persistence.Column;

public class ActivityLogReq {
    private String name;
    private String personalCode;
    private String cardPAN;
    private String applicationType;

    public String getName() {
        return name;
    }

    public ActivityLogReq setName(String name) {
        this.name = name;
        return this;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public ActivityLogReq setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
        return this;
    }

    public String getCardPAN() {
        return cardPAN;
    }

    public ActivityLogReq setCardPAN(String cardPAN) {
        this.cardPAN = cardPAN;
        return this;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public ActivityLogReq setApplicationType(String applicationType) {
        this.applicationType = applicationType;
        return this;
    }
}
