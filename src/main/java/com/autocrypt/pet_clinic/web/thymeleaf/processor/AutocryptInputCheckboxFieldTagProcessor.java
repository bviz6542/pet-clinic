package com.autocrypt.pet_clinic.web.thymeleaf.processor;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.web.bind.WebDataBinder;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.exceptions.TemplateProcessingException;
import org.thymeleaf.model.AttributeValueQuotes;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.model.IStandaloneElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring6.context.IThymeleafBindStatus;
import org.thymeleaf.spring6.dialect.SpringStandardDialect;
import org.thymeleaf.spring6.requestdata.RequestDataValueProcessorUtils;
import org.thymeleaf.spring6.util.SpringSelectedValueComparator;
import org.thymeleaf.standard.util.StandardProcessorUtils;
import org.unbescape.html.HtmlEscape;

public class AutocryptInputCheckboxFieldTagProcessor extends AutocryptAbstractSpringFieldTagProcessorWrapper {
    private final boolean renderHiddenMarkersBeforeCheckboxes;


    public AutocryptInputCheckboxFieldTagProcessor(final String dialectPrefix) {
        this(dialectPrefix, SpringStandardDialect.DEFAULT_RENDER_HIDDEN_MARKERS_BEFORE_CHECKBOXES);
    }


    public AutocryptInputCheckboxFieldTagProcessor(
        final String dialectPrefix,
        final boolean renderHiddenMarkersBeforeCheckboxes) {
        super(dialectPrefix, AC_CHECKBOX_TAG_NAME, null, null, true);
        this.renderHiddenMarkersBeforeCheckboxes = renderHiddenMarkersBeforeCheckboxes;
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

        String value = null;
        boolean checked = false;

        Object boundValue = bindStatus.getValue();
        final Class<?> valueType = bindStatus.getValueType();

        if (Boolean.class.equals(valueType) || boolean.class.equals(valueType)) {

            if (boundValue instanceof String) {
                boundValue = Boolean.valueOf((String) boundValue);
            }
            final Boolean booleanValue = (boundValue != null ? (Boolean) boundValue : Boolean.FALSE);
            value = "true";
            checked = booleanValue.booleanValue();

        } else {

            value = tag.getAttributeValue(this.valueAttributeDefinition.getAttributeName());
            if (value == null) {
                throw new TemplateProcessingException(
                    "Attribute \"value\" is required in \"input(checkbox)\" tags " +
                        "when binding to non-boolean values");
            }

            checked = SpringSelectedValueComparator.isSelected(bindStatus, HtmlEscape.unescapeHtml(value));

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
        StandardProcessorUtils.setAttribute(
            structureHandler,
            this.valueAttributeDefinition,
            VALUE_ATTR_NAME,
            RequestDataValueProcessorUtils.processFormFieldValue(context, name, value, "checkbox"));
        if (checked) {
            StandardProcessorUtils.setAttribute(
                structureHandler,
                this.checkedAttributeDefinition,
                CHECKED_ATTR_NAME,
                CHECKED_ATTR_NAME);
        } else {
            structureHandler.removeAttribute(this.checkedAttributeDefinition.getAttributeName());
        }

        if (!isDisabled(tag)) {

            /*
             * Non-disabled checkboxes need an additional <input type="hidden"> in order to note their presence in
             * the HTML document. Given unchecked checkboxes are not sent by browsers as a result of form submission,
             * this is the only way to differentiate between a checkbox that is unchecked and a checkbox that was
             * never displayed or is disabled.
             */

            final IModelFactory modelFactory = context.getModelFactory();

            final IModel hiddenTagModel = modelFactory.createModel();

            final String hiddenName = WebDataBinder.DEFAULT_FIELD_MARKER_PREFIX + name;
            final String hiddenValue = "on";

            final Map<String, String> hiddenAttributes = new LinkedHashMap<String, String>(4, 1.0f);
            hiddenAttributes.put(TYPE_ATTR_NAME, "hidden");
            hiddenAttributes.put(NAME_ATTR_NAME, hiddenName);
            hiddenAttributes.put(
                VALUE_ATTR_NAME,
                RequestDataValueProcessorUtils.processFormFieldValue(context, hiddenName, hiddenValue, "hidden"));

            final IStandaloneElementTag hiddenTag =
                modelFactory.createStandaloneElementTag(
                    AC_INPUT_TAG_NAME,
                    hiddenAttributes,
                    AttributeValueQuotes.DOUBLE,
                    false,
                    true);

            hiddenTagModel.add(hiddenTag);

            if (this.renderHiddenMarkersBeforeCheckboxes) {
                structureHandler.insertBefore(hiddenTagModel);
            } else {
                structureHandler.insertImmediatelyAfter(hiddenTagModel, false);
            }

        }


    }


    private final boolean isDisabled(final IProcessableElementTag tag) {
        // Disabled = attribute "disabled" exists
        return tag.hasAttribute(this.disabledAttributeDefinition.getAttributeName());
    }
}
