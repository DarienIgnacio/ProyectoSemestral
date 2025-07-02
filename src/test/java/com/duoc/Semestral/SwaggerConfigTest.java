package com.duoc.Semestral;

import com.duoc.Semestral.Config.Swagger;
import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = { Swagger.class })
@TestPropertySource(properties = {
        "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration"
})
public class SwaggerConfigTest {

    @Test
    public void testSwaggerConfigurationCreation() {
        Swagger swaggerConfig = new Swagger();
        OpenAPI openAPI = swaggerConfig.openAPI();

        assertNotNull(openAPI);
        assertNotNull(openAPI.getInfo());
        assertEquals("Proyecto Semestral API", openAPI.getInfo().getTitle());
        assertEquals("v1.0.0", openAPI.getInfo().getVersion());
        assertNotNull(openAPI.getServers());
        assertTrue(openAPI.getServers().size() > 0);
    }
}

