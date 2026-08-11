package my.integration.zoho.exception;

public class PDFGeneratorException extends Exception {
    public PDFGeneratorException(String message) {
        super(message);
    }

    public PDFGeneratorException(String message, Throwable cause) {
        super(message, cause);
    }

    public PDFGeneratorException(Throwable cause) {
        super(cause);
    }
}
