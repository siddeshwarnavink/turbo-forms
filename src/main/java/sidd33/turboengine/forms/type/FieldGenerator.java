package sidd33.turboengine.forms.type;

import java.util.Map;

import sidd33.turboengine.forms.annotation.FormField;

public interface FieldGenerator {
   public String renderContent(FormField formField, Object value, Map<String, Object> config, String errorMessage);
   public String renderScripts(FormField formField, Map<String, Object> config, boolean initilized);
   public String renderStyles(FormField formField);
}
