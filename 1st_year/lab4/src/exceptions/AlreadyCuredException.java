package exceptions;

public class AlreadyCuredException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Oh, the Goat is already cured!";
    }
}
