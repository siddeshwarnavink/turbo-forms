package sidd33.turboengine.forms.test;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import sidd33.turboengine.forms.annotation.FormField;
import sidd33.turboengine.forms.type.FormData;
import sidd33.turboengine.forms.type.FormFieldType;

public class TestForm implements FormData {
    @FormField(label = "Name", fieldType = FormFieldType.TEXTINPUT)
    @NotEmpty(message = "Name is required")
    private String name;

    @FormField(label = "Date Time", fieldType = FormFieldType.DATETIME)
    @NotNull(message = "Date Time is required")
    @Min(value = 1, message = "Date Time is required")
    private Long dateTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTimeString) {
        if (dateTimeString != null && !dateTimeString.isEmpty()) {
            this.dateTime = Long.parseLong(dateTimeString);
        }
    }
}
