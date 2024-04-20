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
}
