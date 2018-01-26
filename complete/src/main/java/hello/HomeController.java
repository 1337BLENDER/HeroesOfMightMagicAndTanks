package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
public class HomeController {
/*
    @RequestMapping("/lk")
    public String main() {
        return "lk";
    }

    @RequestMapping(value = "/")
    public ModelAndView home(Locale locale,Model model) {
        return new ModelAndView("/resources/index.html");
    }

    @RequestMapping(value = "/registration")
    public ModelAndView registration(Locale locale,Model model) {
        return new ModelAndView("/resources/registration.html");
    }

    @RequestMapping(value = "/login")
    public ModelAndView login() {
        return new ModelAndView("/resources/index.html");
    }
*/

    @RequestMapping("/")
    public String home(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "index";
    }

    @RequestMapping("/registration")
    public String registration(){
        return "registration";
    }

    @RequestMapping("/lk")
    public String lk(){return "lk";}
}