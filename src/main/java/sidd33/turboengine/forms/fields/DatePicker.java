package sidd33.turboengine.forms.fields;

import sidd33.turboengine.forms.annotation.FormField;
import sidd33.turboengine.forms.type.FieldGenerator;

public class DatePicker implements FieldGenerator {
    private static boolean scriptInitilized = false;
    private static boolean styleInitilized = false;

    @Override
    public String renderContent(FormField formField) {
        StringBuilder builder = new StringBuilder();

        builder.append("<label>" + formField.label() + "</label>");
        builder.append("<input id=\"" + formField.name() + "\">");
        builder.append("<input type=\"hidden\" name=\"" + formField.name() + "\">");

        return builder.toString();
    }

    @Override
    public String renderScripts(FormField formField) {
        StringBuilder builder = new StringBuilder();

        if (!scriptInitilized) {
            builder.append("<script src=\"https://cdn.jsdelivr.net/npm/flatpickr\"></script>");
            builder.append("""
                    <script>
                        const flatpickrConifg = {
                            enableTime: true,
                            dateFormat: "d-m-Y H:i",
                            onClose: function (selectedDates, dateStr, instance) {
                                const isoDate = instance.latestSelectedDateObj.toISOString();
                                instance.input.value = isoDate;
                                document.querySelector(`input[name=${instance.input.id}]`).value = isoDate;
                            }
                        };
                    </script>""");
        }
        scriptInitilized = true;

        builder.append("<script>flatpickr('#" + formField.name() + "', flatpickrConifg);</script>");

        return builder.toString();
    }

    @Override
    public String renderStyles(FormField formField) {
        StringBuilder builder = new StringBuilder();

        if(!styleInitilized) {
            builder.append("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css\">");
        }
        styleInitilized = true;

        return builder.toString();
    }
}
