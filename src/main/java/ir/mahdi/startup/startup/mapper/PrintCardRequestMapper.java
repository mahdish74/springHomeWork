package ir.mahdi.startup.startup.mapper;


import ir.mahdi.startup.startup.dto.out.PrintRequestRes;
import ir.mahdi.startup.startup.dto.in.PrintRequestReq;
import ir.mahdi.startup.startup.infrastructure.annotation.ExecuteTime;
import ir.mahdi.startup.startup.model.embeddable.PrintRequestEmbeddedId;
import ir.mahdi.startup.startup.model.entity.PrintRequest;

import java.util.Date;
import java.util.Random;

public class PrintCardRequestMapper {

    public static PrintRequestRes mapPrintRequestToPrintRequestRes(PrintRequest printRequest) {
        return new PrintRequestRes()
                .setIpAddress(printRequest.getIpAddress())
                .setCardPAN(printRequest.getCardPAN())
                .setBranchCode(printRequest.getBranchCode())
                .setIssueDate(printRequest.getIssueDate())
                .setPersonalCode(printRequest.getPersonalCode())
                .setCode(printRequest.getCode());
    }

    public static PrintRequest mapPrintRequestReqToPrintRequest(PrintRequestReq printRequestReq) {
        return new PrintRequest()
                .setEmbeddedId(new PrintRequestEmbeddedId(printRequestReq.getBranchCode(), printRequestReq.getIpAddress()))
                .setIpAddress(printRequestReq.getIpAddress())
                .setCardPAN(printRequestReq.getCardPAN())
                .setBranchCode(printRequestReq.getBranchCode())
                .setIssueDate(new Date())
                .setPersonalCode(printRequestReq.getPersonalCode());
    }
}
