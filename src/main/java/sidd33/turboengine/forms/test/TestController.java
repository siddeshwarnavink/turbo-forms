package sidd33.turboengine.forms.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

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
    public ModelAndView postTestRoot(@Valid TestForm formData, BindingResult result) {
        System.out.println(formData);

        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {
            modelAndView.setViewName("test/index");
            modelAndView.addObject("formData", formData);
            return modelAndView;
        }

        modelAndView.setView(new MappingJackson2JsonView());
        return modelAndView;
    }
}
