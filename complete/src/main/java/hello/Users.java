package hello;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "users")
public class Users {
    private int id;
    private String nick;
    private String password;
    private String jabber;
    private int gold;
    private double winrate;
    private int numberOfBattles;
    private int experience;
    private String role;
    private Collection<Friends> friends;
    private Characters character;
    private Cities city;
    private Army army;

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE,generator = "userSeq")
    @SequenceGenerator(name = "userSeq",sequenceName = "userSeq")
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nick", nullable = false, length = 255)
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "jabber", nullable = true, length =255)
    public String getJabber() {
        return jabber;
    }

    public void setJabber(String jabber) {
        this.jabber = jabber;
    }

    @Basic
    @Column(name = "gold", nullable = false)
    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Basic
    @Column(name = "winrate", nullable = false, precision = 2)
    public double getWinrate() {
        return winrate;
    }

    public void setWinrate(double winrate) {
        this.winrate = winrate;
    }

    @Basic
    @Column(name = "number_of_battles", nullable = false)
    public int getNumberOfBattles() {
        return numberOfBattles;
    }

    public void setNumberOfBattles(int numberOfBattles) {
        this.numberOfBattles = numberOfBattles;
    }

    @Basic
    @Column(name = "experience", nullable = false)
    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Basic
    @Column(name = "role", nullable = false, length = 255)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (id != users.id) return false;
        if (gold != users.gold) return false;
        if (Double.compare(users.winrate, winrate) != 0) return false;
        if (numberOfBattles != users.numberOfBattles) return false;
        if (experience != users.experience) return false;
        if (nick != null ? !nick.equals(users.nick) : users.nick != null) return false;
        if (password != null ? !password.equals(users.password) : users.password != null) return false;
        if (jabber != null ? !jabber.equals(users.jabber) : users.jabber != null) return false;
        if (role != null ? !role.equals(users.role) : users.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (nick != null ? nick.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (jabber != null ? jabber.hashCode() : 0);
        result = 31 * result + gold;
        temp = Double.doubleToLongBits(winrate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + numberOfBattles;
        result = 31 * result + experience;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "user1")
    public Collection<Friends> getFriends() {
        return friends;
    }

    public void setFriends(Collection<Friends> friends) {
        this.friends = friends;
    }

    @ManyToOne
    @JoinColumn(name = "character_id", referencedColumnName = "id", nullable = false)
    public Characters getCharacter() {
        return character;
    }

    public void setCharacter(Characters character) {
        this.character = character;
    }

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
    }

    @ManyToOne
    @JoinColumn(name = "army_id", referencedColumnName = "id", nullable = false)
    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }

    public Users() {
    }

    public Users(String nick, String password, String jabber, int gold, double winrate, int numberOfBattles, int experience, String role, Characters character, Cities city, Army army) {
        this.nick = nick;
        this.password = password;
        this.jabber = jabber;
        this.gold = gold;
        this.winrate = winrate;
        this.numberOfBattles = numberOfBattles;
        this.experience = experience;
        this.role = role;
        this.character = character;
        this.city = city;
        this.army = army;
        this.friends=new ArrayList<>();
    }

    public Users(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", nick='" + nick + '\'' +
                ", password='" + password + '\'' +
                ", jabber='" + jabber + '\'' +
                ", gold=" + gold +
                ", winrate=" + winrate +
                ", numberOfBattles=" + numberOfBattles +
                ", experience=" + experience +
                ", role='" + role + '\'' +
                //", friends=" + friends +
                ", character=" + character +
                ", city=" + city +
                ", army=" + army +
                '}';
    }
}
