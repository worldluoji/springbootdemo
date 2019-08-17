package excpetions;

import lombok.Getter;

public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}
