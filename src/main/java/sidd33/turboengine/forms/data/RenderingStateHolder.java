package sidd33.turboengine.forms.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import sidd33.turboengine.forms.taglibs.Script;
import sidd33.turboengine.forms.taglibs.Style;
import sidd33.turboengine.forms.type.FormData;
import sidd33.turboengine.forms.type.FormFieldType;

@Component
public class RenderingStateHolder implements Serializable {
    private Class<? extends FormData> formDataClass;
    private Object formData;
    private Script script = new Script();
    private Style style = new Style();
    private Map<String, Object> model;
    private Map<String, String> errors = new HashMap<>();
    private Set<String> renderedFields = new HashSet<>();
    private Set<FormFieldType> styleInitilized = new HashSet<>();

    public void setModel(Map<String, Object> model) {
        this.model = model;

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

    public Object getFormData() {
        return formData;
    }

    public void setFormData(Object formData) {
        this.formData = formData;
    }

    public Class<? extends FormData> getFormDataClass() {
        return formDataClass;
    }

    public void setFormDataClass(Class<? extends FormData> formDataClass) {
        this.formDataClass = formDataClass;
    }

    public Set<FormFieldType> getStyleInitilized() {
        return styleInitilized;
    }

    public Set<String> getRenderedFields() {
        return renderedFields;
    }

    private Set<FormFieldType> renderedScripts = new HashSet<>();

    public Set<FormFieldType> getRenderedScripts() {
        return renderedScripts;
    }

    public Style getStyle() {
        return style;
    }

    public Script getScript() {
        return script;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "RenderingStateHolder{" +
                "formDataClass=" + formDataClass +
                ", formData=" + formData +
                ", script=" + script +
                ", style=" + style +
                ", model=" + model +
                ", errors=" + errors +
                ", renderedFields=" + renderedFields +
                ", styleInitilized=" + styleInitilized +
                ", renderedScripts=" + renderedScripts +
                '}';
    }
}
