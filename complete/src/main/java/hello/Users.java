package hello;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class Users {
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private int id;
    @NotEmpty(message = "*Пожалуйста введите свой ник")
    @Length(min = 3, message = "*Ник должен содержать как минимум 3 символа")
    private String nick;
    @NotEmpty(message = "*Пожалуйста введите свой пароль")
    @Length(min = 3, message = "*Пароль должен содержать как минимум 3 символа")
    private String password;
    private String jabber;
    //    private int gold;
    private double winrate;
    private int numberOfBattles;
    //    private int experience;
    @JsonIgnore
    private Collection<Friends> friends;
    private Characters character;
    //    private Cities city;
    private Army army;
//    private AppUser appUser;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "userSeq")
    @SequenceGenerator(name = "userSeq", sequenceName = "userSeq")
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nick", nullable = false, length = 255, unique = true)
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }


    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    @Basic
    @Column(name = "jabber", nullable = true, length = 255)
    public String getJabber() {
        return jabber;
    }

    public void setJabber(String jabber) {
        this.jabber = jabber;
    }

    /*
        @Basic
        @Column(name = "gold", nullable = true)
        public int getGold() {
            return gold;
        }

        public void setGold(int gold) {
            this.gold = gold;
        }
    */
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

    /*
        @Basic
        @Column(name = "experience", nullable = true)
        public int getExperience() {
            return experience;
        }

        public void setExperience(int experience) {
            this.experience = experience;
        }
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;

        Users users = (Users) o;

        if (id != users.id) return false;
        //      if (gold != users.gold) return false;
        if (Double.compare(users.winrate, winrate) != 0) return false;
        if (numberOfBattles != users.numberOfBattles) return false;
        //    if (experience != users.experience) return false;
        if (!nick.equals(users.nick)) return false;
        if (!password.equals(users.password)) return false;
        if (!jabber.equals(users.jabber)) return false;
        if (friends != null ? !friends.equals(users.friends) : users.friends != null) return false;
        if (!character.equals(users.character)) return false;
        //  if (city != null ? !city.equals(users.city) : users.city != null) return false;
        if (army != null ? !army.equals(users.army) : users.army != null) return false;
        return true;
        //return appUser != null ? appUser.equals(users.appUser) : users.appUser == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + nick.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + jabber.hashCode();
        //result = 31 * result + gold;
        temp = Double.doubleToLongBits(winrate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + numberOfBattles;
        //result = 31 * result + experience;
        result = 31 * result + (friends != null ? friends.hashCode() : 0);
        result = 31 * result + character.hashCode();
        //result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (army != null ? army.hashCode() : 0);
        //result = 31 * result + (appUser != null ? appUser.hashCode() : 0);
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

    /*
        @ManyToOne
        @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = true)
        public Cities getCity() {
            return city;
        }

        public void setCity(Cities city) {
            this.city = city;
        }
    /*
        @OneToOne
        @JoinColumn(name = "app_user_id")
        public AppUser getAppUser() {
            return appUser;
        }

        public void setAppUser(AppUser appUser) {
            this.appUser = appUser;
        }
    */
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

    public Users(String nick, String password, String jabber, int gold, double winrate, int numberOfBattles, int experience, Characters character, Cities city, Army army) {
        this.nick = nick;
        setPassword(password);
        this.jabber = jabber;
        //      this.gold = gold;
        this.winrate = winrate;
        this.numberOfBattles = numberOfBattles;
        //    this.experience = experience;
        this.character = character;
        //  this.city = city;
        this.army = army;
    }

    public Users(String nick, String password, String jabber, double winrate, int numberOfBattles, Characters character, Army army) {
        this.nick = nick;
        setPassword(password);
        this.jabber = jabber;
        this.winrate = winrate;
        this.numberOfBattles = numberOfBattles;
        this.character = character;
        this.army = army;
    }

    public Users(String nick, String password, String jabber, double winrate, int numberOfBattles, Characters character, Army army, AppUser appUser) {
        this.nick = nick;
        setPassword(password);
        this.jabber = jabber;
        this.winrate = winrate;
        this.numberOfBattles = numberOfBattles;
        this.character = character;
        this.army = army;
        //      this.appUser = appUser;
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
                ", winrate=" + winrate +
                ", numberOfBattles=" + numberOfBattles +
//                ", appUser=" + appUser +
                ", character=" + character +
                ", army=" + army +
                '}';
    }
}
