package sidd33.turboengine.forms.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sidd33.turboengine.forms.annotation.WithForm;

@Controller
@RequestMapping("/test")
public class TestController {
   @GetMapping("/")
   @WithForm(TestForm.class)
   public String getTestRoot() {
       return "test/index";
   }
}
