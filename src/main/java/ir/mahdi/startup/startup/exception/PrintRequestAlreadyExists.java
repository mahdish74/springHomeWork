package ir.mahdi.startup.startup.exception;

public class PrintRequestAlreadyExists extends BadRequest {
    public PrintRequestAlreadyExists() {
        super(PrintRequestAlreadyExists.class.getSimpleName());
    }
}
