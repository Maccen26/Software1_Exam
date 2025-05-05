package dtu.domain;

public class ErrorMessage extends Exception {
    private static final long serialVersionUID = 2025050512345678901L;
    public ErrorMessage(String message) {
        super(message);
    }
}