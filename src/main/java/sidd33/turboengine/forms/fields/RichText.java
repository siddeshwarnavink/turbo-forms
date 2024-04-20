package sidd33.turboengine.forms.fields;

import java.util.Map;

import sidd33.turboengine.forms.annotation.FormField;
import sidd33.turboengine.forms.type.FieldGenerator;

public class RichText implements FieldGenerator {

    @Override
    public String renderContent(FormField formField, Object value, Map<String, Object> config, String errorMessage) {
        String strValue = "";
        if (value instanceof String) {
            strValue = (String) value;
        }
        String height = config.containsKey("height") ? (String) config.get("height") : "300px";

        StringBuilder builder = new StringBuilder();

        builder.append("<div class=\"mb-3\">");
        builder
                .append("<label class=\"form-label\">" + formField.label() + "</label>");
        builder.append("<input type=\"hidden\" id=\"" + formField.name() + "-editorContent\" name=\""
                + formField.name() + "\" value=\"" + strValue + "\">");
        builder.append("<div class=\"form-control\" id=\"" + formField.name() + "-editor\"");
        builder.append("style=\"height:" + height + ";\">");
        builder.append("</div>\n");
        if (errorMessage != null) {
            builder.append("<div class=\"invalid-feedback d-block\">" + errorMessage + "</div>");
        }
        builder.append("</div>");
        builder.append("<script>");
        builder.append("const " + formField.name() + "__MD = `" + strValue + "`;");
        builder.append("</script>");

        return builder.toString();
    }

    @Override
    public String renderScripts(FormField formField, Map<String, Object> config, boolean initilized) {
        StringBuilder builder = new StringBuilder();

        if (!initilized) {
            builder.append("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/quill/2.0.0/quill.min.js\"></script>");
            builder.append("<script src=\"https://unpkg.com/turndown/dist/turndown.js\"></script>");
            builder.append(
                    "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/showdown/2.1.0/showdown.min.js\"></script>");
            builder.append("<script>const turndownService = new TurndownService();");
            builder.append("const showdown_converter = new showdown.Converter();</script>");
        }

        builder.append("<script>");
        builder.append("const " + formField.name() + "__quill = new Quill('#" + formField.name() + "-editor',{");
        builder.append("theme: 'snow'");
        builder.append("});");
        builder.append(formField.name() + "__quill.root.innerHTML = showdown_converter.makeHtml(" + formField.name()
                + "__MD);");
        builder.append(formField.name() + "__quill.on('text-change', function () {");
        builder.append("const editorContent = document.getElementById('" + formField.name() + "-editorContent');");
        builder.append("editorContent.value = showdown_converter.makeHtml(" + formField.name()
                + "__quill.root.innerHTML);");
        builder.append("    });");
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
