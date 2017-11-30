package hello;
import javax.persistence.*;

@Entity
@Table(name = "race")
public class Race {
    private int id;
    private String name;

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE,generator = "raceSeq")
    @SequenceGenerator(name = "raceSeq",sequenceName = "raceSeq")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Race race = (Race) o;

        if (id != race.id) return false;
        if (name != null ? !name.equals(race.name) : race.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        return String.format("Race[id=%d,name=%s]",id,name);
    }

    public Race() {
    }

    public Race(String name) {
        this.name = name;
    }

    public Race(int id) {
        this.id = id;
    }
}
