package sidd33.turboengine.forms.data;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Component
public class RenderingStateHolder {
    private static Map<String, Object> model;
    private static Map<String, String> errors = new HashMap<>();

    public static Map<String, String> getErrors() {
        return errors;
    }

    public static Map<String, Object> getModel() {
        return model;
    }

    public static void setModel(Map<String, Object> model) {
        RenderingStateHolder.model = model;

        for (String attributeName : model.keySet()) {
            if (model.get(attributeName) instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) model.get(attributeName);

                if (bindingResult.hasErrors()) {
                    for (ObjectError error : bindingResult.getAllErrors()) {
                        if (error instanceof FieldError) {
                            FieldError fieldError = (FieldError) error;
                            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
                        }
                    }
                }

                break;
            }
        }
    }
}
