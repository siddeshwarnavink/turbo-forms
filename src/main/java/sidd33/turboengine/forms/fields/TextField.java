package sidd33.turboengine.forms.fields;

import java.util.Map;

import sidd33.turboengine.forms.annotation.FormField;
import sidd33.turboengine.forms.type.FieldGenerator;

public class TextField implements FieldGenerator {
    @Override
    public String renderContent(FormField formField, Object value, Map<String, Object> config, String errorMessage) {
        String strValue = "";
        if (value instanceof String) {
            strValue = (String) value;
        }

        StringBuilder builder = new StringBuilder();

        builder.append("<div class=\"mb-2\">");
        builder.append("<label class=\"form-label\">" + formField.label() + "</label>");
        builder.append("<input class=\"form-control\" ")
                .append("name=\"").append(formField.name()).append("\" ")
                .append("type=\"").append(config.get("type")).append("\" ")
                .append("value=\"").append(strValue).append("\"")
                .append(config.containsKey("required") ? "required" : "")
                .append(">");
        builder.append(config.containsKey("hint") ? "<div class=\"form-text\">" + config.get("hint") + "</div>" : "");
        if (errorMessage != null) {
            builder.append("<div class=\"invalid-feedback d-block\">" + errorMessage + "</div>");
        }
        builder.append("</div>");

        return builder.toString();
    }

    @Override
    public String renderScripts(FormField formField, Map<String, Object> config, boolean initilized) {
        return null;
    }

    @Override
    public String renderStyles(FormField formField) {
        return null;
    }
}
