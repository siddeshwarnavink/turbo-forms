package sidd33.turboengine.forms.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StringUtilTest {
    @Test
    void shouldCapitalizeFirstLetter() {
        String test = "siddeshwar";
        String output = StringUtil.capitalizeFirstLetter(test);

        assertEquals(output, "Siddeshwar");
        assertEquals(test.length(), output.length());
    }
}
