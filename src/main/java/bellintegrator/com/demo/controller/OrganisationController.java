package bellintegrator.com.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrganisationController {

    @GetMapping("/hello")
    public String returnHello() {
        ObjectMapper mapper = new ObjectMapper();
//        mapper.writerWithView(Country.class, )
        return "<h1>Hello</h1>";
    }


}
