package hello.lightEntities;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;

public class LightUser {
    private String name;
    private String Jabber;
    private int battles;
    private double winrate;
    private String character;
    private Boolean isFriend;
    private Boolean isYou;
    private Boolean restorable;
    private Boolean isDeleted;
    private List<LightUnitInArmy> army;

    public LightUser(String name, String jabber, int battles, double winrate, String character) {
        this.name = name;
        Jabber = jabber;
        this.battles = battles;
        this.winrate = winrate;
        this.character = character;
        army = new ArrayList<>();
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Boolean getRestorable() {
        return restorable;
    }

    public void setRestorable(Boolean restorable) {
        this.restorable = restorable;
    }

    public Boolean getYou() {
        return isYou;
    }

    public void setYou(Boolean you) {
        isYou = you;
    }

    public Boolean getFriend() {
        return isFriend;
    }

    public void setFriend(Boolean friend) {
        isFriend = friend;
    }

    public void addUnits(int number, String name){
        army.add(new LightUnitInArmy(number,name));
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJabber(String jabber) {
        Jabber = jabber;
    }

    public void setBattles(int battles) {
        this.battles = battles;
    }

    public void setWinrate(double winrate) {
        this.winrate = winrate;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getName() {
        return name;
    }

    public String getJabber() {
        return Jabber;
    }

    public int getBattles() {
        return battles;
    }

    public double getWinrate() {
        return winrate;
    }

    public String getCharacter() {
        return character;
    }

    public List<LightUnitInArmy> getArmy() {
        return army;
    }
}
