package ir.mahdi.startup.startup.exception;

public class PrintRequestNotExists extends BadRequest {
    public PrintRequestNotExists() {
        super(PrintRequestNotExists.class.getSimpleName());
    }
}
