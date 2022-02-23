package ca.wonderfish.filmstreamingapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HomeController {

    @RequestMapping("/")
    public void handleFoo(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

}
