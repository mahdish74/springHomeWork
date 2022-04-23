package ir.mahdi.startup.startup.controller;

import ir.mahdi.startup.startup.dto.in.PrintRequestReq;
import ir.mahdi.startup.startup.dto.out.PrintRequestRes;
import ir.mahdi.startup.startup.service.PrintRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/printRequest")
public class PrintRequestController {
    private final PrintRequestService printRequestService;

    public PrintRequestController(PrintRequestService printRequestService) {
        this.printRequestService = printRequestService;
    }

    @GetMapping("/getForm")
    public String addBookView(Model model) {
        model.addAttribute("printRequestReq", new PrintRequestReq());
        return "create";
    }

    @RequestMapping("/saveDetails")
    public String saveDetails(@Valid @ModelAttribute PrintRequestReq printRequestReq, BindingResult bindingResult, Model model) {
        String destPage;
        if (bindingResult.hasErrors()) {
            model.addAttribute("printRequestReq", new PrintRequestReq());
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            bindingResult.getModel().put("errors", objectErrors.get(0));
            model.addAttribute("hasError", true);
            model.addAttribute("message", bindingResult.getAllErrors().get(0).getDefaultMessage());
            destPage="create";
        } else {
            PrintRequestRes printRequestRes = printRequestService.insertPrintRequest(printRequestReq);
            model.addAttribute("printRequestRes",printRequestRes);
            destPage="result";
        }
        return destPage;
    }
}
