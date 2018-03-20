package hello;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "characters")
public class Characters {
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "charSeq")
    @SequenceGenerator(name = "charSeq", sequenceName = "charSeq")
    @Column(name = "id", nullable = false)
    private int id;
    private String clazz;
    @ManyToOne(targetEntity = Race.class)
    @JoinColumn(name = "race_id", referencedColumnName = "id", nullable = false)
    private Race race;
    private String iconUrl;//маленькая иконка(можно верезать из большой)
    private String battleIconUrl;//вид в бою (большая картинка во весь рост)
    //private Collection<CharactersAbilities> abilites;


    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "class", nullable = false, length = 255)
    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }


    @Basic
    @Column(name = "iconUrl", nullable = false)
    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Basic
    @Column(name = "battleIconUrl", nullable = false)
    public String getBattleIconUrl() {
        return battleIconUrl;
    }

    public void setBattleIconUrl(String battleIconUrl) {
        this.battleIconUrl = battleIconUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Characters that = (Characters) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (clazz != null ? !clazz.equals(that.clazz) : that.clazz != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + id;
        result = 31 * result + (clazz != null ? clazz.hashCode() : 0);
        return result;
    }


    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = Abilities.class)
    @JoinTable(name = "character_abilities", joinColumns = @JoinColumn(name = "character_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "ability_id", referencedColumnName = "id"))
    private List<Abilities> abilities = new ArrayList<>();

    public List<Abilities> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Abilities> abilities) {
        this.abilities = abilities;
    }

    public void addAbility(Abilities ability) {
        this.abilities.add(ability);
    }

    public Characters() {
    }

    public Characters(String name, String clazz, Race race, String iconUrl, String battleIconUrl) {
        this.name = name;
        this.clazz = clazz;
        this.race = race;
        this.iconUrl = iconUrl;
        this.battleIconUrl = battleIconUrl;
    }

    @Override
    public String toString() {
        return "Characters{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", clazz='" + clazz + '\'' +
                ", race=" + race +
                ", iconUrl='" + iconUrl + '\'' +
                ", battleIconUrl='" + battleIconUrl + '\'' +
                ", abilities=" + abilities +
                '}';
    }
}
