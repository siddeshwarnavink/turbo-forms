package sidd33.turboengine.forms.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;
import sidd33.turboengine.forms.annotation.WithForm;

@Controller
@RequestMapping("/test")
public class TestController {
    @GetMapping("/")
    @WithForm(TestForm.class)
    public String getTestRoot(Model model) {
        model.addAttribute("formData", new TestForm());
        return "test/index";
    }

    @PostMapping(value = "/")
    @WithForm(TestForm.class)
    public String postTestRoot(@Valid TestForm formData, BindingResult result, Model model) throws JsonProcessingException {
        model.addAttribute("formData", formData);

        if (result.hasErrors()) {
            return "test/index";
        }

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(formData);
    }
}
