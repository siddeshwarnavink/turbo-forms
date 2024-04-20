package sidd33.turboengine.forms.fields;

import sidd33.turboengine.forms.annotation.FormField;
import sidd33.turboengine.forms.type.FieldGenerator;

public class TextField implements FieldGenerator {  
	@Override
	public String renderContent(FormField formField, Object value, String errorMessage) {
        String strValue = "";
        if(value instanceof String) {
            strValue = (String) value;
        }

        StringBuilder builder = new StringBuilder();

        builder.append("<div class=\"mb-2\">");
        builder.append("<label class=\"form-label\">" + formField.label() + "</label>");
        builder.append("<input class=\"form-control\" name=\"" + formField.name() + "\" value=\"" + strValue + "\">");
        if(errorMessage != null) {
            builder.append("<div class=\"invalid-feedback d-block\">" + errorMessage + "</div>");
        }
        builder.append("</div>");
        
        return builder.toString();
	}

	@Override
	public String renderScripts(FormField formField, boolean initilized) {
        return null;
	}

	@Override
	public String renderStyles(FormField formField) {
        return null;
	}
}
