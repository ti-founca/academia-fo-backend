package py.edu.odontounca.academiafoback.shared.exception;

import java.time.Instant;
import java.util.Map;

public abstract class ApplicationError extends RuntimeException {
    private final String errorCode;
    private final Instant timestamp;
    private final Map<String, Object> details;

    protected ApplicationError(String errorCode, String message, Map<String, Object> details, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.timestamp = Instant.now();
        this.details = details != null ? Map.copyOf(details) : Map.of();
    }

    protected ApplicationError(String errorCode, String message){
        this(errorCode, message, Map.of(), null);
    }

    protected ApplicationError(String errorCode, String message, Map<String, Object> details){
        this(errorCode, message, details, null);
    }

    public String getErrorCode() {
        return errorCode;
    }
    public Instant getTimestamp() {
        return timestamp;
    }

    public Map<String, Object> getDetails() {
        return details;
    }
}
