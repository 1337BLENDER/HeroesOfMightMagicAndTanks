package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/service")
public class ServiceController {

    private UsersService usersService;
    private CharactersService charactersService;
    private UnitsService unitsService;

    @Autowired
    public ServiceController(UsersService usersService, CharactersService charactersService, UnitsService unitsService) {
        this.usersService = usersService;
        this.charactersService = charactersService;
        this.unitsService = unitsService;
    }

    @RequestMapping("/getLeaderboard")
    public @ResponseBody Leader[] getLeaderboard(String limit){
        int intLimit=Integer.parseInt(limit);
        return usersService.getLeaderboard(intLimit);
    }
    @RequestMapping("/checkNick")
    public  @ResponseBody Response checkNick(String nick){
        if(usersService.getByNick(nick)==null){
            return new Response("false");
        }else {
            return new Response("true");
        }
    }

    @RequestMapping("/getCharacters")
    public @ResponseBody Characters[] getCharacters(){
        List<Characters> characters=new ArrayList<>();
        Characters[] arr;
        for(Characters character : charactersService.getAll()){
            characters.add(character);
        }
        arr=new Characters[characters.size()];
        for(int i=0;i<characters.size();i++){
            arr[i]=characters.get(i);
        }
        return arr;
    }

    @RequestMapping("/getUnits")
    public @ResponseBody Units[] getUnits(){
        List<Units> units = new ArrayList<>();
        Units[] arr;
        for(Units unit:unitsService.getAll()){
            units.add(unit);
        }
        arr=new Units[units.size()];
        for(int i=0;i<units.size();i++){
            arr[i]=units.get(i);
        }
        return arr;
    }

}
