package sidd33.turboengine.forms.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sidd33.turboengine.forms.annotation.WithForm;

@Controller
@RequestMapping("/test")
public class TestController {
    @GetMapping("/")
    @WithForm(TestForm.class)
    public String getTestRoot() {
        return "test/index";
    }

    @PostMapping("/")
    @WithForm(TestForm.class)
    public String postTestRoot(@Valid TestForm formData, BindingResult result, Model model) {
        model.addAttribute("formData", formData);

        if (result.hasErrors()) {
            return "test/index";
        }

        return "redirect:/";
    }
}
