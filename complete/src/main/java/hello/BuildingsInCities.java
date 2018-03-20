package hello;

import javax.persistence.*;

@Entity
@Table(name = "buildings_in_cities", schema = "public", catalog = "postgres")
public class BuildingsInCities {
    private int id;
    private Cities city;
    private Buildings building;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BuildingsInCities that = (BuildingsInCities) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
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
    @JoinColumn(name = "building_id", referencedColumnName = "id", nullable = false)
    public Buildings getBuilding() {
        return building;
    }

    public void setBuilding(Buildings building) {
        this.building = building;
    }

    public BuildingsInCities() {
    }

    public BuildingsInCities(Cities city, Buildings building) {
        this.city = city;
        this.building = building;
    }

    public BuildingsInCities(int id) {
        this.id = id;
    }
}
