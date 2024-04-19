package sidd33.turboengine.forms.taglibs;

import java.io.IOException;
import java.util.Optional;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import lombok.Setter;
import sidd33.turboengine.forms.annotation.FormField;
import sidd33.turboengine.forms.annotation.FormFieldGeneratorProcessor;
import sidd33.turboengine.forms.annotation.WithFormProcessor;
import sidd33.turboengine.forms.type.FieldGenerator;

public class FormControl extends SimpleTagSupport {
    @Setter
    private String name;

    @Override
    public void doTag() throws JspException, IOException {
        if (WithFormProcessor.formDataClass != null) {
            Optional<FormField> optField = WithFormProcessor.formFields.stream()
                    .filter(f -> f.name().equals(name))
                    .findFirst();

            if (optField.isPresent()) {
                FormField field = optField.get();
                FieldGenerator generator =  FormFieldGeneratorProcessor.generators.get(field.fieldType());

                if(generator == null) {
                    throw new JspException("Field Generator not configured for type " + field.fieldType());
                }

                getJspContext().getOut().write(generator.renderContent(field));
            } else {
                throw new JspException("FormField not found in FormData");
            }
        } else {
            throw new JspException("No FormData class specified");
        }
    }
}
