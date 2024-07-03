package com.tareas.app.tareas_app.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    /**
     * Route que lanza el home de la application (index.html)
     * @param viewModel
     * @return
     */
    @GetMapping("/")
    public String home(Model viewModel) {
        return "index.html";
    }

}
