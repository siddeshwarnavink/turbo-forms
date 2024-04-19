package sidd33.turboengine.forms.fields;

import sidd33.turboengine.forms.annotation.FormField;
import sidd33.turboengine.forms.type.FieldGenerator;

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
