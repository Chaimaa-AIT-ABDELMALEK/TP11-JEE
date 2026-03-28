package ma.fstg.security.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String afficherLogin() {
        return "login";
    }

    @GetMapping("/home")
    public String afficherHome() {
        return "home";
    }

    @GetMapping("/user")
    public String afficherUser() {
        return "user";
    }

    @GetMapping("/admin")
    public String afficherAdmin() {
        return "admin";
    }
}