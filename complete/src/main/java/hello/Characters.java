package hello;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "characters")
public class Characters {
    private String name;
    private int id;
    private String clazz;
    private Race race;
    private Collection<CharactersAbilities> abilites;
    private Set<Abilities> abilities;

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE,generator = "charSeq")
    @SequenceGenerator(name="charSeq",sequenceName = "charSeq")
    @Column(name = "id", nullable = false)
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

    @ManyToOne
    @JoinColumn(name = "race_id", referencedColumnName = "id", nullable = false)
    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    @OneToMany(mappedBy = "character")
    public Collection<CharactersAbilities> getAbilites() {
        return abilites;
    }

    public void setAbilites(Collection<CharactersAbilities> abilites) {
        this.abilites = abilites;
    }

    public Characters() {
    }

    public Characters(String name, String clazz, Race race) {
        this.name = name;
        this.clazz = clazz;
        this.race = race;
    }

    public Characters(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String ret="Characters{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", clazz='" + clazz + '\'' +
                ", race=" + race +
                ", abilites=";
                for(CharactersAbilities charactersAbilities:abilites){
                    ret+=charactersAbilities.getAbility().toString();
                }
                ret+='}';
        return ret;
    }
}
