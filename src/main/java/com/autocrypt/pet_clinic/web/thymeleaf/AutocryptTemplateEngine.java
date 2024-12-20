package com.autocrypt.pet_clinic.web.thymeleaf;

import com.autocrypt.pet_clinic.web.thymeleaf.dialect.AcDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutocryptTemplateEngine {

    @Bean
    public AcDialect acInputDialect() {
        return new AcDialect();
    }
}
