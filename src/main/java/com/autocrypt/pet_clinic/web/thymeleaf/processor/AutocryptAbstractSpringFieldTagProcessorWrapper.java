package com.autocrypt.pet_clinic.web.thymeleaf.processor;

import org.thymeleaf.spring6.processor.AbstractSpringFieldTagProcessor;

public abstract class AutocryptAbstractSpringFieldTagProcessorWrapper extends AbstractSpringFieldTagProcessor {
    protected static final String AC_INPUT_TAG_NAME = "ac-input";
    protected static final String AC_SELECT_TAG_NAME = "ac-select";
    protected static final String AC_TEXTAREA_TAG_NAME = "ac-textarea";
    protected static final String AC_CHECKBOX_TAG_NAME = "ac-checkbox";
    protected static final String AC_RADIO_TAG_NAME = "ac-radio";

    public AutocryptAbstractSpringFieldTagProcessorWrapper(
        String dialectPrefix,
        String elementName,
        String discriminatorAttrName,
        String[] discriminatorAttrValues,
        boolean removeAttribute) {
        super(dialectPrefix, elementName, discriminatorAttrName, discriminatorAttrValues, removeAttribute);
    }
}
