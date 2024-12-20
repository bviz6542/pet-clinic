package com.autocrypt.pet_clinic.config.web.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring6.context.IThymeleafBindStatus;
import org.thymeleaf.spring6.requestdata.RequestDataValueProcessorUtils;
import org.thymeleaf.spring6.util.SpringValueFormatter;
import org.thymeleaf.standard.util.StandardProcessorUtils;

public class AutocryptSelectInputHiddenFieldTagProcessor extends AutocryptAbstractSpringFieldTagProcessorWrapper {
    public AutocryptSelectInputHiddenFieldTagProcessor(final String dialectPrefix) {
        super(dialectPrefix, AC_SELECT_TAG_NAME, null, null, true);
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

        // Thanks to precedence, this should have already been computed
        final String type = tag.getAttributeValue(this.typeAttributeDefinition.getAttributeName());

        // Apply the conversions (editor), depending on type (no conversion for "number" and "range"
        // Also, no escaping needed as attribute values are always escaped by default
        final String value =
            applyConversion(type) ?
            SpringValueFormatter.getDisplayString(bindStatus.getValue(), bindStatus.getEditor(), true) :
            SpringValueFormatter.getDisplayString(bindStatus.getActualValue(), true);

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
            RequestDataValueProcessorUtils.processFormFieldValue(context, name, value, type));
    }


    private static boolean applyConversion(final String type) {
        return !(type != null && ("number".equalsIgnoreCase(type) || "range".equalsIgnoreCase(type)));
    }
}
