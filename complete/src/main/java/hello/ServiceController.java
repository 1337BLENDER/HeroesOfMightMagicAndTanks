package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    UsersService usersService;

    @RequestMapping("/getLeaderboard")
    public @ResponseBody Leader[] getLeaderboard(String limit){
        int intLimit=Integer.parseInt(limit);
        return usersService.getLeaderboard(intLimit);
    }
}
