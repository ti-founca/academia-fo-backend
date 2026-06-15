package py.edu.odontounca.academiafoback.shared.exception;

import java.util.Map;

public class NotFoundError extends ApplicationError {
    public NotFoundError(String resource, Object id){
        super(
                "RESOURCE_NOT_FOUND",
                String.format("No se encontró %s con id '%s'", resource, id),
                Map.of("resource", resource, "id", id)
        );
    }
}
