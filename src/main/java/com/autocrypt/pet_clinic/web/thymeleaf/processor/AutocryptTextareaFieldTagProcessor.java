package com.autocrypt.pet_clinic.web.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring6.context.IThymeleafBindStatus;
import org.thymeleaf.spring6.requestdata.RequestDataValueProcessorUtils;
import org.thymeleaf.spring6.util.SpringValueFormatter;
import org.thymeleaf.standard.util.StandardProcessorUtils;
import org.thymeleaf.util.StringUtils;

public class AutocryptTextareaFieldTagProcessor extends AutocryptAbstractSpringFieldTagProcessorWrapper {
    public AutocryptTextareaFieldTagProcessor(final String dialectPrefix) {
        super(dialectPrefix, AC_TEXTAREA_TAG_NAME, null, null, true);
    }


    @Override
    protected void doProcess(
        final ITemplateContext context,
        final IProcessableElementTag tag,
        final AttributeName attributeName, final String attributeValue,
        final IThymeleafBindStatus bindStatus, final IElementTagStructureHandler structureHandler) {

        String name = bindStatus.getExpression();
        name = (name == null ? "" : name);

        final String id = computeId(context, tag, name, false);

        final String value = SpringValueFormatter.getDisplayString(bindStatus.getValue(), bindStatus.getEditor(), true);

        String processedValue =
            RequestDataValueProcessorUtils.processFormFieldValue(context, name, value, "textarea");

        if (!StringUtils.isEmpty(processedValue)) {
            final char c0 = processedValue.charAt(0);
            if (c0 == '\n') {
                processedValue = '\n' + processedValue;
            } else if (c0 == '\r' && processedValue.length() > 1 && processedValue.charAt(1) == '\n') {
                processedValue = "\r\n" + processedValue;
            } else if (c0 == '\r') {
                processedValue = '\r' + processedValue;
            }
        }

        StandardProcessorUtils.setAttribute(
            structureHandler,
            this.idAttributeDefinition,
            ID_ATTR_NAME,
            id); // No need to escape: this comes from an existing 'id' or from a token
        StandardProcessorUtils.setAttribute(
            structureHandler,
            this.nameAttributeDefinition,
            NAME_ATTR_NAME,
            name); // No need to escape: this is a java-valid token

        structureHandler.setBody((processedValue == null ? "" : processedValue), false);

    }
}
