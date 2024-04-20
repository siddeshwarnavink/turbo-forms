package sidd33.turboengine.forms.fields;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Map;

import sidd33.turboengine.forms.annotation.FormField;
import sidd33.turboengine.forms.type.FieldGenerator;

public class DatePicker implements FieldGenerator {
    @Override
    public String renderContent(FormField formField, Object value, Map<String, Object> config, String errorMessage) {
        Long timestamp = null;
        String strTimestampValue = "";
        String strValue = "";
        if (value instanceof Long) {
            timestamp = (Long) value;
            strTimestampValue = timestamp.toString();

            Date date = new Date(timestamp * 1000);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            strValue = sdf.format(date);
        }

        StringBuilder builder = new StringBuilder();

        builder.append("<div class=\"mb-2\">");
        builder.append("<label class=\"form-label\">" + formField.label() + "</label>");
        builder.append("<input value=\"" + strValue + "\" class=\"form-control\" id=\"" + formField.name() + "\">");
        builder.append(
                "<input value=\"" + strTimestampValue + "\" type=\"hidden\" name=\"" + formField.name() + "\">");
        if (errorMessage != null) {
            builder.append("<div class=\"invalid-feedback d-block\">" + errorMessage + "</div>");
        }
        builder.append("</div>");

        return builder.toString();
    }

    @Override
    public String renderScripts(FormField formField, Map<String, Object> config, boolean initilized) {
        StringBuilder builder = new StringBuilder();

        boolean disableTime = config.containsKey("time") && config.get("time") == "false";

        if (!initilized) {
            builder.append("<script src=\"https://cdn.jsdelivr.net/npm/flatpickr\"></script>");
        }

        builder.append("<script>\n")
            .append("    const flatpickrConifg__" + formField.name() + " = {\n")
            .append(!disableTime ?  "        enableTime: true,\n" : "")
            .append("        dateFormat: \"d-m-Y H:i\",\n")
            .append(config.containsKey("minDate") ?  "        minDate: \"" + config.get("minDate") + "\",\n" : "")
            .append(config.containsKey("maxDate") ?  "        maxDate: \"" + config.get("minDate") + "\",\n" : "")
            .append(config.containsKey("dateRange") ?  "        mode: \"range\",\n" : "")
            .append("        onClose: function (selectedDates, dateStr, instance) {\n")
            .append("            const timestamp = Math.floor(instance.latestSelectedDateObj.getTime() / 1000);\n")
            .append("            instance.input.value = timestamp;\n")
            .append("            document.querySelector(`input[name=${instance.input.id}]`).value = timestamp;\n")
            .append("        }\n")
            .append("    };\n");
        builder.append("flatpickr('#" + formField.name() + "', flatpickrConifg__" + formField.name() + ");</script>");

        return builder.toString();
    }

    @Override
    public String renderStyles(FormField formField) {
        StringBuilder builder = new StringBuilder();

        builder.append(
                "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css\">");

        return builder.toString();
    }
}
