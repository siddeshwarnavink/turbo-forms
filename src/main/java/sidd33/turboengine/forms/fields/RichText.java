package sidd33.turboengine.forms.fields;

import java.util.Map;

import sidd33.turboengine.forms.annotation.FormField;
import sidd33.turboengine.forms.data.RenderArgs;
import sidd33.turboengine.forms.type.FieldGenerator;

public class RichText implements FieldGenerator {

    @Override
    public String renderContent(RenderArgs args) {
        String strValue = "";
        if (args.getValue() instanceof String) {
            strValue = (String) args.getValue();
        }
        String height = args.getConfig().containsKey("height") ? (String) args.getConfig().get("height") : "300px";

        StringBuilder builder = new StringBuilder();

        builder.append("<div class=\"mb-3\">");
        builder
                .append("<label class=\"form-label\">" + args.getFormField().label() + "</label>");
        builder.append("<input type=\"hidden\" id=\"" + args.getFormField().name() + "-editorContent\" name=\""
                + args.getFormField().name() + "\" value=\"" + strValue + "\">");
        builder.append("<div class=\"form-control\" id=\"" + args.getFormField().name() + "-editor\"");
        builder.append("style=\"height:" + height + ";\">");
        builder.append("</div>\n");
        if (args.getErrorMessage() != null) {
            builder.append("<div class=\"invalid-feedback d-block\">" + args.getErrorMessage() + "</div>");
        }
        builder.append("</div>");
        builder.append("<script>");
        builder.append("const " + args.getFormField().name() + "__MD = `" + strValue + "`;");
        builder.append("</script>");

        return builder.toString();
    }

    @Override
    public String renderScripts(RenderArgs args) {
        StringBuilder builder = new StringBuilder();

        if (!args.isInitilized()) {
            builder.append("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/quill/2.0.0/quill.min.js\"></script>");
            builder.append(
                    "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/showdown/2.1.0/showdown.min.js\"></script>");
            builder.append("<script>const showdown_converter = new showdown.Converter();</script>");
        }

        builder.append("<script>");
        builder.append("const " + args.getFormField().name() + "__quill = new Quill('#" + args.getFormField().name() + "-editor',{");
        builder.append("theme: 'snow'");
        builder.append("});");
        builder.append(args.getFormField().name() + "__quill.root.innerHTML = showdown_converter.makeHtml(" + args.getFormField().name()
                + "__MD);");
        builder.append(args.getFormField().name() + "__quill.on('text-change', function () {");
        builder.append("const editorContent = document.getElementById('" + args.getFormField().name() + "-editorContent');");
        builder.append("editorContent.value = showdown_converter.makeHtml(" + args.getFormField().name()
                + "__quill.root.innerHTML);");
        builder.append("});");
        builder.append("</script>");

        return builder.toString();
    }

    @Override
    public String renderStyles(FormField formField) {
        StringBuilder builder = new StringBuilder();

        builder.append(
                "<link href=\"https://cdn.jsdelivr.net/npm/quill@2.0.0/dist/quill.snow.css\" rel=\"stylesheet\" />");

        return builder.toString();
    }

}
