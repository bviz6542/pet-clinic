package com.autocrypt.pet_clinic.config.web.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.exceptions.TemplateProcessingException;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring6.context.IThymeleafBindStatus;
import org.thymeleaf.spring6.requestdata.RequestDataValueProcessorUtils;
import org.thymeleaf.spring6.util.SpringSelectedValueComparator;
import org.thymeleaf.standard.util.StandardProcessorUtils;
import org.unbescape.html.HtmlEscape;

public class AutocryptInputRadioFieldTagProcessor extends AutocryptAbstractSpringFieldTagProcessorWrapper {
    public AutocryptInputRadioFieldTagProcessor(final String dialectPrefix) {
        super(dialectPrefix, AC_RADIO_TAG_NAME, null, null, true);
    }


    @Override
    protected void doProcess(
        final ITemplateContext context,
        final IProcessableElementTag tag,
        final AttributeName attributeName, final String attributeValue,
        final IThymeleafBindStatus bindStatus, final IElementTagStructureHandler structureHandler) {

        String name = bindStatus.getExpression();
        name = (name == null ? "" : name);

        final String id = computeId(context, tag, name, true);

        final String value = tag.getAttributeValue(this.valueAttributeDefinition.getAttributeName());
        if (value == null) {
            throw new TemplateProcessingException(
                "Attribute \"value\" is required in \"input(radio)\" tags");
        }

        final boolean checked =
            SpringSelectedValueComparator.isSelected(bindStatus, HtmlEscape.unescapeHtml(value));

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
        StandardProcessorUtils.setAttribute(
            structureHandler,
            this.valueAttributeDefinition,
            VALUE_ATTR_NAME,
            RequestDataValueProcessorUtils.processFormFieldValue(context, name, value, "radio"));

        if (checked) {
            StandardProcessorUtils.setAttribute(
                structureHandler,
                this.checkedAttributeDefinition,
                CHECKED_ATTR_NAME,
                CHECKED_ATTR_NAME);
        } else {
            structureHandler.removeAttribute(this.checkedAttributeDefinition.getAttributeName());
        }

    }
}
