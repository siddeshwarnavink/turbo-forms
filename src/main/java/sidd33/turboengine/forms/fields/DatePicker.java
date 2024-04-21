package sidd33.turboengine.forms.fields;

import java.sql.Date;
import java.text.SimpleDateFormat;

import sidd33.turboengine.forms.annotation.FormField;
import sidd33.turboengine.forms.data.RenderArgs;
import sidd33.turboengine.forms.type.FieldGenerator;

public class DatePicker implements FieldGenerator {
    @Override
    public String renderContent(RenderArgs args) {
        Long timestamp = null;
        String strTimestampValue = "";
        String strValue = "";
        if (args.getValue() instanceof Long) {
            timestamp = (Long) args.getValue();
            strTimestampValue = timestamp.toString();

            Date date = new Date(timestamp * 1000);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            strValue = sdf.format(date);
        }

        StringBuilder builder = new StringBuilder();

        builder.append("<div class=\"mb-2\">");
        builder.append("<label class=\"form-label\">" + args.getFormField().label() + "</label>");
        builder.append(
                "<input value=\"" + strValue + "\" class=\"form-control\" id=\"" + args.getFormField().name() + "\">");
        builder.append(
                "<input value=\"" + strTimestampValue + "\" type=\"hidden\" name=\"" + args.getFormField().name()
                        + "\">");
        if (args.getErrorMessage() != null) {
            builder.append("<div class=\"invalid-feedback d-block\">" + args.getErrorMessage() + "</div>");
        }
        builder.append("</div>");

        return builder.toString();
    }

    @Override
    public String renderScripts(RenderArgs args) {
        StringBuilder builder = new StringBuilder();

        boolean disableTime = args.getConfig().containsKey("time") && args.getConfig().get("time") == "false";

        if (!args.isInitilized()) {
            builder.append("<script src=\"https://cdn.jsdelivr.net/npm/flatpickr\"></script>");
        }

        builder.append("<script>\n")
                .append("const flatpickrConifg__" + args.getFormField().name() + " = {")
                .append(!disableTime ? "enableTime: true,\n" : "")
                .append("dateFormat: \"d-m-Y H:i\",")
                .append(args.getConfig().containsKey("minDate") ? "minDate: \"" + args.getConfig().get("minDate") + "\"," : "")
                .append(args.getConfig().containsKey("maxDate") ? "maxDate: \"" + args.getConfig().get("minDate") + "\"," : "")
                .append(args.getConfig().containsKey("dateRange") ? "mode: \"range\"," : "")
                .append("onClose: function (selectedDates, dateStr, instance) {")
                .append("const timestamp = Math.floor(instance.latestSelectedDateObj.getTime() / 1000);")
                .append("instance.input.value = timestamp;")
                .append("document.querySelector(`input[name=${instance.input.id}]`).value = timestamp;")
                .append("}")
                .append("};");
        builder.append("flatpickr('#" + args.getFormField().name() + "', flatpickrConifg__" + args.getFormField().name()
                + ");</script>");

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
