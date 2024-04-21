package sidd33.turboengine.forms.fields;

import sidd33.turboengine.forms.annotation.FormField;
import sidd33.turboengine.forms.data.RenderArgs;
import sidd33.turboengine.forms.type.FieldGenerator;

public class ErrorOnly implements FieldGenerator {
	@Override
	public String renderContent(RenderArgs args) {
        if (args.getErrorMessage() != null) {
            return "<div class=\"invalid-feedback d-block\">" + args.getErrorMessage() + "</div>";
        }
        return "";
	}

	@Override
	public String renderScripts(RenderArgs args) {
        return null;
	}

	@Override
	public String renderStyles(FormField formField) {
        return null;
	} 
}
