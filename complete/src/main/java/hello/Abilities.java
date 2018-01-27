package hello;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "abilities")
public class Abilities {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator = "abilities_id_seq")
    @SequenceGenerator(name="abilities_id_seq",sequenceName = "abilities_id_seq")
    @Column(name = "id", nullable = false)
    private int id;
    private String name;
    private String type;
    private int level;
    private int manaPoints;
    private int damage;
    private String description;
    private String target;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, unique = true,length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 255)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "level", nullable = true)
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Basic
    @Column(name = "mana_points", nullable = false)
    public int getManaPoints() {
        return manaPoints;
    }

    public void setManaPoints(int manaPoints) {
        this.manaPoints = manaPoints;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "target")
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Basic
    @Column(name = "damage", nullable = false)
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Abilities)) return false;

        Abilities abilities = (Abilities) o;

        if (id != abilities.id) return false;
        if (level != abilities.level) return false;
        if (manaPoints != abilities.manaPoints) return false;
        if (damage != abilities.damage) return false;
        if (!name.equals(abilities.name)) return false;
        if (type != null ? !type.equals(abilities.type) : abilities.type != null) return false;
        if (description != null ? !description.equals(abilities.description) : abilities.description != null)
            return false;
        return target != null ? target.equals(abilities.target) : abilities.target == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + level;
        result = 31 * result + manaPoints;
        result = 31 * result + damage;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (target != null ? target.hashCode() : 0);
        return result;
    }

    public Abilities(){
    }
    public Abilities(String name,String type,int level,int manaPoints,int damage){

        this.name = name;
        this.type = type;
        this.level = level;
        this.manaPoints = manaPoints;
        this.damage = damage;
    }

    public Abilities(String name, String type, int manaPoints, int damage, String description, String target) {
        this.name = name;
        this.type = type;
        this.manaPoints = manaPoints;
        this.damage = damage;
        this.description = description;
        this.target = target;
    }

    public Abilities(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Abilities{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", level=" + level +
                ", manaPoints=" + manaPoints +
                ", damage=" + damage +
                ", description='" + description + '\'' +
                ", target='" + target + '\'' +
                '}';
    }
}
