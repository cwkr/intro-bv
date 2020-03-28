package de.cwkr.intro.bv.spring.infrastructure;

import de.cwkr.intro.bv.spring.IntroBvSpringApplication;
import java.util.Collections;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import({BeanValidatorPluginsConfiguration.class})
public class SwaggerConfiguration {
    private final BuildProperties buildProperties;

    public SwaggerConfiguration(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public Docket defaultApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .protocols(Collections.singleton("http"))
            .select()
            .apis(RequestHandlerSelectors.basePackage(IntroBvSpringApplication.class.getPackageName()))
            .build()
            .useDefaultResponseMessages(false)
            .apiInfo(
                new ApiInfoBuilder()
                    .title(buildProperties.getName())
                    .version(buildProperties.getVersion())
                    .build()
            );
    }
}
