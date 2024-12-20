package com.autocrypt.pet_clinic.config.web.thymeleaf;

import com.autocrypt.pet_clinic.config.web.thymeleaf.dialect.AcDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutocryptTemplateEngine {

    @Bean
    public AcDialect acInputDialect() {
        return new AcDialect();
    }
}
