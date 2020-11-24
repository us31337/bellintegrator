package bellintegrator.com.demo.controller;

import bellintegrator.com.demo.filter.UserFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostTestController {

    @GetMapping("/addnewuser")
    public String setUserFilter(Model model) {
        model.addAttribute("userFilter", new UserFilter());
        return "/content/addnewuser.html";
    }
}
