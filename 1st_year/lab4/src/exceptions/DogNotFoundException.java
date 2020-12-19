package exceptions;

public class DogNotFoundException extends Exception {

    @Override
    public String getMessage() {
        return "Ooooh, unfortunately we have no dog!";
    }




}
