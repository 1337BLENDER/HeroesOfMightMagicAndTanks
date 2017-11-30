package hello;

import javax.persistence.*;

@Entity
@Table(name = "locations")
public class Locations {
    private int id;
    private String name;
    private int level;
    private int experience;
    private int gold;
    private Army army;

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE,generator = "locSeq")
    @SequenceGenerator(name = "locSeq",sequenceName = "locSeq")
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "level", nullable = false)
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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
    @Column(name = "gold", nullable = false)
    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Locations locations = (Locations) o;

        if (id != locations.id) return false;
        if (level != locations.level) return false;
        if (experience != locations.experience) return false;
        if (gold != locations.gold) return false;
        if (name != null ? !name.equals(locations.name) : locations.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + level;
        result = 31 * result + experience;
        result = 31 * result + gold;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "army_id", referencedColumnName = "id", nullable = false)
    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }

    public Locations(){    }

    public Locations(String name, int level, int experience, int gold, Army army) {
        this.name = name;
        this.level = level;
        this.experience = experience;
        this.gold = gold;
        this.army = army;
    }

    @Override
    public String toString() {
        return "Locations{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", experience=" + experience +
                ", gold=" + gold +
                ", army=" + army +
                '}';
    }
}
