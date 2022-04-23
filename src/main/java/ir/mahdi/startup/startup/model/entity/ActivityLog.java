package ir.mahdi.startup.startup.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class ActivityLog {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String personalCode;

    @Column(nullable = false)
    private String cardPAN;

    @Column(nullable = false)
    private String applicationType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityLog that = (ActivityLog) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(personalCode, that.personalCode) && Objects.equals(cardPAN, that.cardPAN) && Objects.equals(applicationType, that.applicationType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, personalCode, cardPAN, applicationType);
    }

    public String getName() {
        return name;
    }

    public ActivityLog setName(String name) {
        this.name = name;
        return this;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public ActivityLog setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
        return this;
    }

    public String getCardPAN() {
        return cardPAN;
    }

    public ActivityLog setCardPAN(String cardPAN) {
        this.cardPAN = cardPAN;
        return this;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public ActivityLog setApplicationType(String applicationType) {
        this.applicationType = applicationType;
        return this;
    }
}
