package com.autocrypt.pet_clinic.web.thymeleaf.dialect;

import com.autocrypt.pet_clinic.web.thymeleaf.processor.AutocryptInputCheckboxFieldTagProcessor;
import com.autocrypt.pet_clinic.web.thymeleaf.processor.AutocryptInputFileFieldTagProcessor;
import com.autocrypt.pet_clinic.web.thymeleaf.processor.AutocryptInputGeneralFieldTagProcessor;
import com.autocrypt.pet_clinic.web.thymeleaf.processor.AutocryptInputPasswordFieldTagProcessor;
import com.autocrypt.pet_clinic.web.thymeleaf.processor.AutocryptInputRadioFieldTagProcessor;
import com.autocrypt.pet_clinic.web.thymeleaf.processor.AutocryptSelectInputHiddenFieldTagProcessor;
import com.autocrypt.pet_clinic.web.thymeleaf.processor.AutocryptTextareaFieldTagProcessor;
import java.util.HashSet;
import java.util.Set;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;

public class AcDialect extends AbstractProcessorDialect {
    public AcDialect() {
        super("Autocrypt Custom Dialect", "th", 1000);
    }

    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        final Set<IProcessor> processors = new HashSet<>();
        processors.add(new AutocryptInputCheckboxFieldTagProcessor(dialectPrefix));
        processors.add(new AutocryptInputFileFieldTagProcessor(dialectPrefix));
        processors.add(new AutocryptInputGeneralFieldTagProcessor(dialectPrefix));
        processors.add(new AutocryptInputPasswordFieldTagProcessor(dialectPrefix));
        processors.add(new AutocryptInputRadioFieldTagProcessor(dialectPrefix));
        processors.add(new AutocryptTextareaFieldTagProcessor(dialectPrefix));
        processors.add(new AutocryptSelectInputHiddenFieldTagProcessor(dialectPrefix));
        return processors;
    }
}
