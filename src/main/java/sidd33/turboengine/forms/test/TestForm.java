package sidd33.turboengine.forms.test;

import jakarta.validation.constraints.NotEmpty;
import sidd33.turboengine.forms.annotation.FormField;
import sidd33.turboengine.forms.data.FormData;
import sidd33.turboengine.forms.data.FormFieldType;

public class TestForm implements FormData {
    @FormField(name = "name", label = "Name", fieldType = FormFieldType.TEXTINPUT)
    @NotEmpty(message = "Name is required")
    private String name;
}
