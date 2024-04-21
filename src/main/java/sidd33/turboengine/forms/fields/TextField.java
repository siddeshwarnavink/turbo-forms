package sidd33.turboengine.forms.fields;

import sidd33.turboengine.forms.annotation.FormField;
import sidd33.turboengine.forms.data.RenderArgs;
import sidd33.turboengine.forms.type.FieldGenerator;

public class TextField implements FieldGenerator {
    @Override
    public String renderContent(RenderArgs args) {
        String strValue = "";
        if (args.getValue() instanceof String) {
            strValue = (String) args.getValue();
        }

        StringBuilder builder = new StringBuilder();

        builder.append("<div class=\"mb-2\">");
        builder.append("<label class=\"form-label\">" + args.getFormField().label() + "</label>");
        builder.append("<input class=\"form-control\" ")
                .append("name=\"").append(args.getFormField().name()).append("\" ")
                .append("type=\"").append(args.getConfig().get("type")).append("\" ")
                .append("value=\"").append(strValue).append("\"")
                .append(args.getConfig().containsKey("required") ? "required" : "")
                .append(">");
        builder.append(
                args.getConfig().containsKey("hint")
                        ? "<div class=\"form-text\">" + args.getConfig().get("hint") + "</div>"
                        : "");
        if (args.getErrorMessage() != null) {
            builder.append("<div class=\"invalid-feedback d-block\">" + args.getErrorMessage() + "</div>");
        }
        builder.append("</div>");

        return builder.toString();
    }

    @Override
    public String renderScripts(RenderArgs args) {
        return null;
    }

    @Override
    public String renderStyles(FormField formField) {
        return null;
    }
}
