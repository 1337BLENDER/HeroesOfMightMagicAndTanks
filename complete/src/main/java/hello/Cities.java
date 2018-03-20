package hello;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cities")
public class Cities {
    private int id;
    private String name;
    //private Collection<BuildingsInCities> buildings;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "citySeq")
    @SequenceGenerator(name = "citySeq", sequenceName = "citySeq")
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

    private Set<Buildings> buildings = new HashSet<>();

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Buildings> getBuildings() {
        return buildings;
    }

    public void setBuildings(Set<Buildings> buildings) {
        this.buildings = buildings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cities cities = (Cities) o;

        if (id != cities.id) return false;
        if (name != cities.name) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }

    public Cities() {
    }

    public Cities(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String ret = "Cities{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", buildings=";
        for (Buildings building : buildings) {
            ret += building.toString() + " ";
        }
        ret += "}";
        return ret;
    }
}
