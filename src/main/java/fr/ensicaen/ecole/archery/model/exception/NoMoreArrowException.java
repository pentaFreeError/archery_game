package fr.ensicaen.ecole.archery.model.exception;

public class NoMoreArrowException extends RuntimeException {
    public NoMoreArrowException() {
        super("No more arrows available!");
    }
}