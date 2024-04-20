package sidd33.turboengine.forms.type;

import sidd33.turboengine.forms.annotation.FormField;

public interface FieldGenerator {
   public String renderContent(FormField formField, Object value, String errorMessage);
   public String renderScripts(FormField formField, boolean initilized);
   public String renderStyles(FormField formField);
}
