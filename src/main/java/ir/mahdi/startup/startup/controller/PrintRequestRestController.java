package ir.mahdi.startup.startup.controller;

import ir.mahdi.startup.startup.dto.in.PrintRequestReq;
import ir.mahdi.startup.startup.dto.out.PrintRequestRes;
import ir.mahdi.startup.startup.infrastructure.aspect.CalExecuteTime;
import ir.mahdi.startup.startup.service.PrintRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
public class PrintRequestRestController {
    Logger logger = LoggerFactory.getLogger(PrintRequestRestController.class);

    private final PrintRequestService printRequestService;

    public PrintRequestRestController(PrintRequestService printRequestService) {
        this.printRequestService = printRequestService;
    }

    @GetMapping("/api/PrintRequest/getAll/{pageNo}/{pageSize}")
    public List<PrintRequestRes> getAll(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, @RequestHeader Map<String, String> headers) {

        headers.forEach((key, value) -> logger.info((key + "    " + value)));
        return printRequestService.getAllPrintRequest(pageNo, pageSize);
    }

    @GetMapping("/api/PrintRequest/getByCode/{code}")
    public PrintRequestRes getByCode(@PathVariable @Valid Long code) {
        return printRequestService.getByPrintRequestCode(code);
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

