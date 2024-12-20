package com.autocrypt.pet_clinic.web.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring6.context.IThymeleafBindStatus;
import org.thymeleaf.spring6.requestdata.RequestDataValueProcessorUtils;
import org.thymeleaf.standard.util.StandardProcessorUtils;

public class AutocryptInputPasswordFieldTagProcessor extends AutocryptAbstractSpringFieldTagProcessorWrapper {
    public static final String PASSWORD_INPUT_TYPE_ATTR_VALUE = "password";

    public AutocryptInputPasswordFieldTagProcessor(final String dialectPrefix) {
        super(dialectPrefix, AC_INPUT_TAG_NAME, TYPE_ATTR_NAME, new String[]{PASSWORD_INPUT_TYPE_ATTR_VALUE}, true);
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
            RequestDataValueProcessorUtils.processFormFieldValue(context, name, "", "password"));

    }
}
