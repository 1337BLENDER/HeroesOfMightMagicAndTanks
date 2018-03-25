package hello.lightEntities;

import hello.Characters;

import java.util.ArrayList;
import java.util.List;

public class MediumUser {
    private String name;
    private String Jabber;
    private int battles;
    private double winrate;
    private Characters character;
    private List<MediumUnitsInArmy> army;

    public MediumUser(String name, String jabber, int battles, double winrate, Characters character) {
        this.name = name;
        Jabber = jabber;
        this.battles = battles;
        this.winrate = winrate;
        this.character = character;
        army=new ArrayList<>();
    }

    public void addUnit(MediumUnitsInArmy unitsInArmy){
        army.add(unitsInArmy);
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

    public void setCharacter(Characters character) {
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

    public Characters getCharacter() {
        return character;
    }

    public List<MediumUnitsInArmy> getArmy() {
        return army;
    }
}
