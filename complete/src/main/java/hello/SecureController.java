package hello;

import hello.lightEntities.LightFriend;
import hello.lightEntities.LightUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/secure")
public class SecureController {

    @Autowired
    @Qualifier("sessionRegistry")
    private SessionRegistry sessionRegistry;
    private UsersService usersService;
    private CharactersService charactersService;
    private UnitsService unitsService;
    private final ArmyService armyService;
    private final UnitsInArmyService unitsInArmyService;
    private FriendsService friendsService;
    private FriendRequestService friendRequestService;

    @Autowired
    public SecureController(UsersService usersService, CharactersService charactersService, UnitsService unitsService, ArmyService armyService, UnitsInArmyService unitsInArmyService, FriendsService friendsService, FriendRequestService friendRequestService) {
        this.usersService = usersService;
        this.charactersService = charactersService;
        this.unitsService = unitsService;
        this.armyService = armyService;
        this.unitsInArmyService = unitsInArmyService;
        this.friendsService = friendsService;
        this.friendRequestService = friendRequestService;
    }

    public List<String> getUsersFromSessionRegistry() {
        List<String> list = sessionRegistry.getAllPrincipals().stream()
                .filter(u -> !sessionRegistry.getAllSessions(u, false).isEmpty())
                .map(Object::toString)
                .collect(Collectors.toList());
        String name;
        for (int i = 0; i < list.size(); i++) {
            name = list.get(i).split(" ")[2];
            list.set(i, name.substring(0, name.length() - 1));
        }
        return list;
    }

    @RequestMapping("/getFriends")
    public @ResponseBody
    List<LightFriend> getFriends() {
        List<LightFriend> friendNames = new ArrayList<>();
        String nick = SecurityContextHolder.getContext().getAuthentication().getName();
        Iterable<FriendRequest> requests = friendRequestService.getBySender(nick);
        String tempNick;
        Boolean tempOnline;
        Boolean tempIsFriend;
        Boolean tempIsDeleted;
        int i=0;
        for (Friends friend : friendsService.getAllByUser1(nick)) {
            tempNick = friend.getUser2().getNick();
            tempOnline = false;
            tempIsFriend = true;
            tempIsDeleted = true;
            for (FriendRequest request : requests) {
                i++;
                if (tempNick.equals(request.getReceiver())) {
                    tempIsFriend = false;
                    if(!request.getCanceled()){
                        tempIsDeleted = false;
                    }
                }
            }
            if(i==0){
                tempIsDeleted = false;
            }
            if (tempIsFriend) {
                for (String name : getUsersFromSessionRegistry()) {
                    if (name.equals(tempNick)) {
                        tempOnline = true;
                        break;
                    }
                }
            }
            friendNames.add(new LightFriend(tempNick, tempOnline, tempIsFriend, tempIsDeleted));
        }
        return friendNames;
    }

    @RequestMapping("/requestFriendship")
    public @ResponseBody String requestFriendship(String name, String message) {
        Users user1 = usersService.getByNick(SecurityContextHolder.getContext().getAuthentication().getName());
        Users user2 = usersService.getByNick(name);
        if(friendsService.getByNicks(user1.getNick(),name)==null) {
            friendsService.saveOrUpdate(new Friends(user1, user2));
        }
        friendRequestService.saveOrUpdate(new FriendRequest(user1.getNick(), name, message));
        return "{\"message\":\"success\"}";
    }

    @RequestMapping("/getFriendRequests")
    public @ResponseBody List<FriendRequest> getFriendRequests() {
        List<FriendRequest> list = new ArrayList<>();
        for (FriendRequest request : friendRequestService.getByReceiverAndCanceled(SecurityContextHolder.getContext().getAuthentication().getName(), false)) {
            if (!request.getCanceled()) {
                list.add(request);
            }
        }
        return list;
    }

    @RequestMapping("/respondFriendRequest")
    public @ResponseBody String respondFriendRequest(String name, String respond) {
        if (respond.equals("apply")) {
            Users user1 = usersService.getByNick(SecurityContextHolder.getContext().getAuthentication().getName());
            Users user2 = usersService.getByNick(name);
            friendsService.saveOrUpdate(new Friends(user1, user2));
            friendRequestService.deleteBySenderAndReceiver(name, user1.getNick());
        } else {
            FriendRequest request = friendRequestService.getBySenderAndReceiverAndCanceled(name, SecurityContextHolder.getContext().getAuthentication().getName(), false);
            request.setCanceled(true);
            friendRequestService.saveOrUpdate(request);
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping("/deleteFriend")
    public @ResponseBody String deleteFriend(String name) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        friendsService.deleteByNicks(user, name);
        friendRequestService.deleteBySenderAndReceiver(user,name);
        if(friendsService.getByNicks(name,user)!=null) {
            FriendRequest friendRequest = new FriendRequest(name, user, "");
            friendRequest.setCanceled(true);
            friendRequestService.saveOrUpdate(friendRequest);
        }
        return "{\"message\":\"success\"}";
    }

    @RequestMapping("/getUser")
    public @ResponseBody
    LightUser getUser(String name) {
        Users user = usersService.getByNick(name);
        if(user!=null) {
            LightUser lightUser = new LightUser(user.getNick(), user.getJabber(), user.getNumberOfBattles(), user.getWinrate(), user.getCharacter().getName());
            for (UnitsInArmy unitsInArmy : user.getArmy().getUnits()) {
                lightUser.addUnits(unitsInArmy.getNumber(), unitsInArmy.getUnit().getName());
            }
            String myName = SecurityContextHolder.getContext().getAuthentication().getName();
            lightUser.setYou(false);
            if(!name.equals(myName)) {
                Users me = usersService.getByNick(myName);
                Boolean isFriend = false;
                for (Friends friends : me.getFriends()) {
                    if (friends.getUser2().getNick().equals(name)) {
                        isFriend = true;
                    }
                }
                lightUser.setFriend(isFriend);
            }else{
                lightUser.setYou(true);
            }
            lightUser.setRestorable(false);
            if(!lightUser.getFriend()) {
                for (Friends friends : user.getFriends()) {
                    if (myName.equals(friends.getUser2().getNick())) {
                        lightUser.setRestorable(true);
                    }
                }
            }
            lightUser.setDeleted(false);
            if(lightUser.getFriend()) {
                if (friendRequestService.getBySenderAndReceiverAndCanceled(myName, name, false) == null) {
                    lightUser.setDeleted(true);
                }
                if(friendRequestService.getBySenderAndReceiver(myName, name)==null){
                    lightUser.setDeleted(false);
                }
            }
            return lightUser;
        }
        else {
            return null;
        }
    }

    @RequestMapping("/restoreFriend")
    public @ResponseBody String restoreFriend(String name){
        Users me = usersService.getByNick(SecurityContextHolder.getContext().getAuthentication().getName());
        Users user = usersService.getByNick(name);
        friendsService.saveOrUpdate(new Friends(me,user));
        friendRequestService.deleteBySenderAndReceiver(name,me.getNick());
        return "{\"message\":\"success\"}";
    }
}
