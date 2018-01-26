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
    @Column(name = "level", nullable = false)
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
        if (o == null || getClass() != o.getClass()) return false;

        Abilities abilities = (Abilities) o;

        if (id != abilities.id) return false;
        if (level != abilities.level) return false;
        if (manaPoints != abilities.manaPoints) return false;
        if (damage != abilities.damage) return false;
        if (name != null ? !name.equals(abilities.name) : abilities.name != null) return false;
        if (type != null ? !type.equals(abilities.type) : abilities.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + level;
        result = 31 * result + manaPoints;
        result = 31 * result + damage;
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
                '}';
    }
}
