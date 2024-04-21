package sidd33.turboengine.forms.type;

import sidd33.turboengine.forms.annotation.FormField;
import sidd33.turboengine.forms.data.RenderArgs;

public interface FieldGenerator {
    public String renderContent(RenderArgs args);

    public String renderScripts(RenderArgs args);

    public String renderStyles(FormField formField);
}
