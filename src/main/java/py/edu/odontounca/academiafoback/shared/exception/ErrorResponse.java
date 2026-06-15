package py.edu.odontounca.academiafoback.shared.exception;

import java.util.Map;

public record ErrorResponse(String error, Map<String, Object> details) {
}
