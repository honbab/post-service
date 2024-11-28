package startspring.exception;

public class ResourceNotExistException extends RuntimeException{
    public ResourceNotExistException(String message) {
        super(message);
    }
}
