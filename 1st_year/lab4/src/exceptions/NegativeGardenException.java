package exceptions;

public class NegativeGardenException extends Exception { 
    @Override
    public String getMessage() {
        return "You destroyed the Garden!";
    }
}
