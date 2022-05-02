package ir.mahdi.startup.startup.service;

import ir.mahdi.startup.startup.dto.in.PrintRequestReq;
import ir.mahdi.startup.startup.dto.out.PrintRequestRes;
import ir.mahdi.startup.startup.exception.IssueDateNotValid;
import ir.mahdi.startup.startup.exception.PrintRequestAlreadyExists;
import ir.mahdi.startup.startup.exception.PrintRequestNotExists;
import ir.mahdi.startup.startup.infrastructure.annotation.ExecuteTime;
import ir.mahdi.startup.startup.infrastructure.utils.Application;
import ir.mahdi.startup.startup.mapper.PrintCardRequestMapper;
import ir.mahdi.startup.startup.model.entity.PrintRequest;
import ir.mahdi.startup.startup.repository.PrintRequestRepo;
import ir.mahdi.startup.startup.repository.custom.PrintRequestCustomRepImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PrintRequestService implements Application {
    private final PrintRequestRepo printRequestRepo;
    private final PrintRequestCustomRepImpl printRequestCustomRep;
    private ApplicationContext applicationContext;

    public PrintRequestService(PrintRequestRepo printRequestRepo, PrintRequestCustomRepImpl printRequestCustomRep) {
        this.printRequestRepo = printRequestRepo;
        this.printRequestCustomRep = printRequestCustomRep;
    }

    @ExecuteTime
    @Transactional(readOnly = true)
    public List<PrintRequestRes> getAllPrintRequest(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("code"));
        Page<PrintRequest> printRequests = printRequestRepo.findAll(paging);
        return printRequests.stream().map(PrintCardRequestMapper::mapPrintRequestToPrintRequestRes).collect(Collectors.toList());
    }

    @Transactional
    @ExecuteTime
    public PrintRequestRes insertPrintRequest(PrintRequestReq printRequestReq) {
        boolean isPrintRequestExists = printRequestRepo.existsPrintRequestByBranchCodeAndIpAddress(printRequestReq.getBranchCode(), printRequestReq.getIpAddress());

        if (isPrintRequestExists) throw new PrintRequestAlreadyExists();

        if (printRequestReq.getIssueDate() == null) throw new IssueDateNotValid();

        PrintRequest newPrintRequest = PrintCardRequestMapper.mapPrintRequestReqToPrintRequest(printRequestReq);
        newPrintRequest.setCode(new Random().nextLong());
        newPrintRequest = printRequestRepo.save(newPrintRequest);
        return PrintCardRequestMapper.mapPrintRequestToPrintRequestRes(newPrintRequest);
    }

    @Transactional
    @ExecuteTime
    public void updateCardPanByCode(String code, String cardPan) {
        boolean isExistPrintRequest = printRequestRepo.existsByCode(code);

        if (isExistPrintRequest) throw new PrintRequestNotExists();

        printRequestRepo.updateCardPanByBranchCode(code, cardPan);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @ExecuteTime
    public PrintRequestRes updatePrintRequest(Long code, PrintRequestReq printRequestReq) {
        Optional<PrintRequest> optionalPrintRequest = printRequestRepo.findByCode(code);

        if (optionalPrintRequest.isEmpty()) throw new PrintRequestNotExists();

        PrintRequest oldPrintRequest = adaptPrintRequestResToPrintRequest(printRequestReq, optionalPrintRequest.get());
        return PrintCardRequestMapper.mapPrintRequestToPrintRequestRes(printRequestRepo.save(oldPrintRequest));
    }

    @ExecuteTime
    public PrintRequestRes getByPrintRequestCode(Long code) {
        Optional<PrintRequest> optionalPrintRequest = printRequestRepo.findByCode(code);

        if (optionalPrintRequest.isEmpty()) throw new PrintRequestNotExists();

        return PrintCardRequestMapper.mapPrintRequestToPrintRequestRes(optionalPrintRequest.get());
    }


    @Transactional
    @ExecuteTime
    public void deletePrintRequest(Long code) {
        Optional<PrintRequest> optionalPrintRequest = printRequestRepo.findByCode(code);
        if (optionalPrintRequest.isEmpty()) throw new IllegalArgumentException();
        printRequestRepo.delete(optionalPrintRequest.get());
    }

    public List<String> getAllIpAddressByBranchCode(String branchCode) {
        return printRequestCustomRep.getAllIpAddressByBranchCode(branchCode);
    }

    private PrintRequest adaptPrintRequestResToPrintRequest(PrintRequestReq printRequestReq, PrintRequest oldPrintRequest) {
        oldPrintRequest.setPersonalCode(printRequestReq.getPersonalCode()).setBranchCode(printRequestReq.getBranchCode()).setCardPAN(printRequestReq.getCardPAN()).setIpAddress(printRequestReq.getIpAddress()).setIssueDate(new Date());
        return oldPrintRequest;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}