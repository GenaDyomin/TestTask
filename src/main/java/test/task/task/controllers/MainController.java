package test.task.task.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import test.task.task.entity.User;
import test.task.task.service.AppearService;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private final AppearService appearService;

    @Autowired
    public MainController(AppearService appearService) {
        this.appearService = appearService;
    }

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user) {
        HashMap<Object, Object> data = new HashMap<>();
        try {
            data.put("profile", user);
            data.put("appears", appearService.getAllAppear(user.getEmail()));
        }
        catch (NullPointerException e){

        }


        model.addAttribute("frontendData", data);

        return "index";
    }
}
