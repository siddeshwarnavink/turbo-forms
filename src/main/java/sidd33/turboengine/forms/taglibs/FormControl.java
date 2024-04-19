package sidd33.turboengine.forms.taglibs;

import java.io.IOException;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import sidd33.turboengine.forms.annotation.WithFormProcessor;

public class FormControl extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        if (WithFormProcessor.formDataClass != null) {
            getJspContext().getOut().write("Got the POJO");
        } else {
            getJspContext().getOut().write("No POJO found!");
        }
    }
}
