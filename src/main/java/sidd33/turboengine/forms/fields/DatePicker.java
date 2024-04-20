package sidd33.turboengine.forms.fields;

import java.sql.Date;
import java.text.SimpleDateFormat;

import sidd33.turboengine.forms.annotation.FormField;
import sidd33.turboengine.forms.type.FieldGenerator;

public class DatePicker implements FieldGenerator {
    @Override
    public String renderContent(FormField formField, Object value, String errorMessage) {
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
    public String renderScripts(FormField formField, boolean initilized) {
        StringBuilder builder = new StringBuilder();

        if (!initilized) {
            builder.append("<script src=\"https://cdn.jsdelivr.net/npm/flatpickr\"></script>");
            builder.append("""
                    <script>
                        const flatpickrConifg = {
                            enableTime: true,
                            dateFormat: "d-m-Y H:i",
                            onClose: function (selectedDates, dateStr, instance) {
                                const timestamp = Math.floor(instance.latestSelectedDateObj.getTime() / 1000);
                                instance.input.value = timestamp;
                                document.querySelector(`input[name=${instance.input.id}]`).value = timestamp;
                            }
                        };
                    </script>""");
        }

        builder.append("<script>flatpickr('#" + formField.name() + "', flatpickrConifg);</script>");

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
