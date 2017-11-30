package hello;

import javax.persistence.*;

@Entity
@Table(name = "levels")
public class Levels {
    private int level;
    private int experience;

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE,generator = "levSeq")
    @SequenceGenerator(name = "levSeq",sequenceName = "levSeq")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Levels levels = (Levels) o;

        if (level != levels.level) return false;
        if (experience != levels.experience) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = level;
        result = 31 * result + experience;
        return result;
    }

    public Levels() {
    }

    public Levels(int level, int experience) {
        this.level = level;
        this.experience = experience;
    }

    public Levels(int level) {
        this.level = level;
    }
}
