package sidd33.turboengine.forms.test;

import jakarta.validation.constraints.NotEmpty;
import sidd33.turboengine.forms.data.FormData;

public class TestForm implements FormData { 
    @NotEmpty(message = "Name is required")
    private String name;
}
