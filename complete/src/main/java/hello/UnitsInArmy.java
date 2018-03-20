package hello;

import javax.persistence.*;

@Entity
@Table(name = "units_in_army", schema = "public", catalog = "postgres")
public class UnitsInArmy {
    private int number;
    private int id;
    private Army army;
    private Units unit;

    @Basic
    @Column(name = "number", nullable = false)
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

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

        UnitsInArmy that = (UnitsInArmy) o;

        if (number != that.number) return false;
        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + id;
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

    @ManyToOne
    @JoinColumn(name = "unit_id", referencedColumnName = "id", nullable = false)
    public Units getUnit() {
        return unit;
    }

    public void setUnit(Units unit) {
        this.unit = unit;
    }

    public UnitsInArmy() {
    }

    public UnitsInArmy(int number, Army army, Units unit) {
        this.number = number;
        this.army = army;
        this.unit = unit;
    }

    public UnitsInArmy(int id) {
        this.id = id;
    }
}
