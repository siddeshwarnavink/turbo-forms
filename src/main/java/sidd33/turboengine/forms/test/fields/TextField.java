package sidd33.turboengine.forms.test.fields;

import org.springframework.stereotype.Component;

import sidd33.turboengine.forms.annotation.FormField;
import sidd33.turboengine.forms.annotation.FormFieldGenerator;
import sidd33.turboengine.forms.data.FormFieldType;
import sidd33.turboengine.forms.type.FieldGenerator;

@Component
@FormFieldGenerator(type = FormFieldType.TEXTINPUT)
public class TextField implements FieldGenerator { 
	@Override
	public String renderContent(FormField formField) {
        StringBuilder builder = new StringBuilder();

        builder.append("<label>" + formField.label() + "</label>");
        builder.append("<input name=" + formField.name() + ">");
        
        return builder.toString();
	}

	@Override
	public String renderScripts(FormField formField) {
        return null;
	}

	@Override
	public String renderStyles(FormField formField) {
        return null;
	}
}
