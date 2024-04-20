package sidd33.turboengine.forms.taglibs;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import lombok.Setter;
import sidd33.turboengine.forms.annotation.FormField;
import sidd33.turboengine.forms.annotation.FormFieldGeneratorProcessor;
import sidd33.turboengine.forms.annotation.WithFormProcessor;
import sidd33.turboengine.forms.data.RenderingStateHolder;
import sidd33.turboengine.forms.type.FieldGenerator;

public class FormControl extends SimpleTagSupport {
    @Setter
    private String name;

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        RenderingStateHolder stateHolder = (RenderingStateHolder) request.getAttribute("formState");
        if (stateHolder == null) {
            throw new JspException("RenderingState not found in request");
        }

        Map<String, String> errors = stateHolder.getErrors();

        if (WithFormProcessor.formDataClass != null) {
            Optional<FormField> optField = WithFormProcessor.formFields.stream()
                    .filter(f -> f.name().equals(name))
                    .findFirst();

            if (optField.isPresent()) {
                FormField field = optField.get();
                FieldGenerator generator = FormFieldGeneratorProcessor.generators.get(field.fieldType());

                if (generator == null) {
                    throw new JspException("Field Generator not configured for type " + field.fieldType());
                }

                String errorMessage = errors.containsKey(field.name()) ? errors.get(field.name()) : null;
                getJspContext().getOut().write(generator.renderContent(field, errorMessage));

                if (!stateHolder.getRenderedFields().contains(field.name())) {
                    boolean initilized = stateHolder.getRenderedScripts().contains(field.fieldType());
                    String renderedScript = generator.renderScripts(field, initilized);
                    if (renderedScript != null) {
                        stateHolder.getScript().getBuilder().append(renderedScript);
                        stateHolder.getRenderedScripts().add(field.fieldType());
                    }

                    stateHolder.getRenderedFields().add(field.name());
                }
            } else {
                throw new JspException("FormField not found in FormData");
            }
        } else {
            throw new JspException("No FormData class specified");
        }
    }
}
