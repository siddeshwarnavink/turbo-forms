package sidd33.turboengine.forms.test;

import jakarta.validation.constraints.NotEmpty;
import sidd33.turboengine.forms.annotation.FormField;
import sidd33.turboengine.forms.type.FormData;
import sidd33.turboengine.forms.type.FormFieldType;

public class TestForm implements FormData {
    @FormField(label = "Name", fieldType = FormFieldType.TEXTINPUT)
    @NotEmpty(message = "Name is required")
    private String name;

	@FormField(label = "Date Time", fieldType = FormFieldType.DATETIME)
    @NotEmpty(message = "Date Time is required")
    private String dateTime;

    @FormField(label = "Another Date Time", fieldType = FormFieldType.DATETIME)
    @NotEmpty(message = "Date Time 2 is required")
    private String dateTime2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDateTime2() {
        return dateTime2;
    }

    public void setDateTime2(String dateTime2) {
        this.dateTime2 = dateTime2;
    }

    @Override
    public String toString() {
        return "TestForm{" +
                "name='" + name + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", dateTime2='" + dateTime2 + '\'' +
                '}';
    }
}
