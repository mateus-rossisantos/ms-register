package com.concentrapay.register.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}

//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//    public static final Contact DEFAULT_CONTACT = new Contact(
//            "Mateus", "https://www.linkedin.com/in/mateus-rossi-dos-santos-75981771/", "mateus.rossisantos@gmail.com");
//
//    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
//            "Api Registro Estabelecimentos ConcentraPay",
//            "API para Registro Estabelecimentos ConcentraPay",
//            "1.0",
//            "urn:tos",
//            DEFAULT_CONTACT,
//            "Apache 2.0",
//            "http://www.apache.org/licenses/LICENSE-2.0",
//            Collections.emptyList()
//    );
//
//    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
//            new HashSet<>(Collections.singletonList("application/json"));
//
//    @Bean
//    public Docket api(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.concentrapay.register.controller"))
//                .paths(PathSelectors.any())
//                .build()
////                .securityContexts(List.of(securityContext()))
////                .securitySchemes(List.of(apiKey()))
//                .useDefaultResponseMessages(true)
//                .apiInfo(DEFAULT_API_INFO)
//                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
//                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
////                .securitySchemes(Arrays.asList(apiKey()))
////                .securityContexts(Arrays.asList(securityContext()));
//    }
//
////    private ApiKey apiKey() {
////        return new ApiKey("Authorization", "JWT", "header");
////    }
////
////    private SecurityContext securityContext() {
////        return SecurityContext.builder().securityReferences(defaultAuth()).build();
////    }
////
////    private List<SecurityReference> defaultAuth() {
////        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
////        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
////        authorizationScopes[0] = authorizationScope;
////        return List.of(new SecurityReference("Authorization", authorizationScopes));
////    }
//
//
//}

