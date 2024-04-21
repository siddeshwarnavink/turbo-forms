package sidd33.turboengine.forms.fields;

import sidd33.turboengine.forms.annotation.FormField;
import sidd33.turboengine.forms.data.RenderArgs;
import sidd33.turboengine.forms.type.FieldGenerator;

public class FileUpload implements FieldGenerator {

    @Override
    public String renderContent(RenderArgs args) {
        StringBuilder builder = new StringBuilder();

        String allowedFormats = "*";
        if (args.getConfig().containsKey("accept") && args.getConfig().get("accept") instanceof String) {
            allowedFormats =  (String) args.getConfig().get("accept");
        }

        String hintText = "Allowed Formats: " + allowedFormats;
        
        String maxSize = "10485760";
        if(args.getConfig().containsKey("size") && args.getConfig().get("size") instanceof String) {
            maxSize = (String) args.getConfig().get("size");
        }

        if (args.getConfig().containsKey("hint")) {
            hintText = args.getConfig().get("hint") + ". " + hintText;
        }

        builder.append("<div class=\"mb-2\">");
        builder.append("<label class=\"form-label\">" + args.getFormField().label() + "</label>");
        builder.append("<input class=\"form-control\" ")
                .append("name=\"").append(args.getFormField().name()).append("\" ")
                .append("type=\"file\" ")
                .append("accept=\"" + allowedFormats + "\"")
                .append("size=\"" + maxSize + "\"")
                .append(args.getConfig().containsKey("required") ? "required" : "")
                .append(args.getConfig().containsKey("multiple") ? "multiple" : "")
                .append(">");
        builder.append("<div class=\"form-text\">" + hintText + "</div>");
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
