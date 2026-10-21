package py.edu.odontounca.academiafoback;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.boot.test.context.SpringBootTest;

// Requiere la base legada (no disponible en CI): solo corre si están definidas las variables DB_*
@SpringBootTest
@EnabledIfEnvironmentVariable(named = "DB_HOST", matches = ".+")
class AcademiaFoBackApplicationTests {

    @Test
    void contextLoads() {
    }

}
