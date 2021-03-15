package exceptions;

public class ValueException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Вы ввели некорректное значение";
    }
}
