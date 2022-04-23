package ir.mahdi.startup.startup.model.embeddable;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PrintRequestEmbeddedId implements Serializable {
    private String branchCode;
    private String ipAddress;

    public PrintRequestEmbeddedId() {

    }

    public PrintRequestEmbeddedId(String branchCode, String ipAddress) {
        this.branchCode = branchCode;
        this.ipAddress = ipAddress;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
