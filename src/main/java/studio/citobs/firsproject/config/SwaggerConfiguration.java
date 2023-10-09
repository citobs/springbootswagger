package studio.citobs.firsproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @package : studio.citobs.testproject.config
 * @name : SwaggerConfiguration.java
 * @date : 2023-10-03
 * @author : citobs
 * @version : 1.0.0
 **/
@Configuration
public class SwaggerConfiguration {

    private static final String API_NAME = "Programmers Spring Boot Application";
    private static final String API_VERSION = "1.0.0";
    private static final String API_DESCRIPTION = "citobs연습용 swagger입니다.";

    // 접속 경로 : http://localhost:8080/swagger-ui/
    @Configuration
    @EnableSwagger2
    public class SwaggerConfig implements WebMvcConfigurer {
        private ApiInfo apiInfo() {

            return new ApiInfoBuilder()
                    .title("citobs")
                    .description("API study")
                    .build();
        }

        @Bean
        public Docket commonApi() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .groupName("example")
                    .apiInfo(this.apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors
                            .basePackage("studio.citobs.firsproject"))
//                    .paths(PathSelectors.ant("/**"))
                    .paths(PathSelectors.any())
                    .build();
        }
    }
}