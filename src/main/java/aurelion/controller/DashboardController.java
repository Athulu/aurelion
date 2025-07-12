package aurelion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @GetMapping("/")
    public String index(Model model) {
//        model.addAttribute(new Person("Jan", "Kowalski", 17));
        return "index";
    }
}
