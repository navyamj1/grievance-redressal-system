package greivance.project.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String SCHEME_NAME = "bearerAuth";

    /**
     * Without a declared scheme Swagger UI has no Authorize button, so every
     * "Try it out" goes out with no Authorization header and comes back 401.
     */
    @Bean
    public OpenAPI apiDefinition() {
        return new OpenAPI()
                .info(new Info()
                        .title("Citizen Grievance Redressal System API")
                        .description("Log in via /auth/login, then paste the returned token into Authorize.")
                        .version("v1"))
                .components(new Components().addSecuritySchemes(SCHEME_NAME,
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList(SCHEME_NAME));
    }
}
