package ir.mahdi.startup.startup.controller;

import ir.mahdi.startup.startup.dto.in.PrintRequestReq;
import ir.mahdi.startup.startup.dto.out.PrintRequestRes;
import ir.mahdi.startup.startup.service.PrintRequestService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;



@RestController
public class PrintRequestRestController {

    private final PrintRequestService printRequestService;

    public PrintRequestRestController(PrintRequestService printRequestService) {
        this.printRequestService = printRequestService;
    }

    @GetMapping("/api/PrintRequest/getAll")
    public List<PrintRequestRes> getAll() {
        return printRequestService.getAllPrintRequest();
    }

    @GetMapping("/api/PrintRequest/getByBranchCode/{branchCode}")
    public PrintRequestRes getByBranchCode(@PathVariable @Valid Long branchCode) {
        return printRequestService.getByPrintRequest(branchCode);
    }


    @PostMapping("/api/PrintRequest/create")
    public PrintRequestRes create(@Valid @RequestBody PrintRequestReq printRequestReq) {
        return printRequestService.insertPrintRequest(printRequestReq);
    }

    @PatchMapping("/api/PrintRequest/updateCardPanByCode/{code}/{cardPan}")
    public void updateCardPanByCode(@PathVariable @Valid String code, @PathVariable @Valid String cardPan) {
        printRequestService.updateCardPanByCode(code, cardPan);
    }

    @PutMapping("/api/PrintRequest/update/{code}")
    public PrintRequestRes update(@PathVariable @Valid Long code, @RequestBody @Valid PrintRequestReq printRequestReq) {
        return printRequestService.updatePrintRequest(code, printRequestReq);
    }

    @DeleteMapping("/api/PrintRequest/delete/{code}")
    public void delete(@PathVariable @Valid Long code) {
        printRequestService.deletePrintRequest(code);
    }

    @GetMapping("/api/PrintRequest/getAllIpAddressByBranchCode/{branchCode}")
    public List<String> getAllIpAddressByBranchCode(@PathVariable @Valid String branchCode) {
        return printRequestService.getAllIpAddressByBranchCode(branchCode);
    }



}

