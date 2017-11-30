package hello;

import javax.persistence.*;

@Entity
@Table(name = "buildings")
public class Buildings {
    private int id;
    private String name;
    private int level;
    private int cost;
    private String type;
    private Integer income;
    private Integer unitNumber;
    private Race race;
    private Units unit;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO,generator = "builSeq")
    @SequenceGenerator(name="builSeq",sequenceName = "builSeq")
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
    @Column(name = "cost", nullable = false)
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
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
    @Column(name = "income", nullable = true)
    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    @Basic
    @Column(name = "unit_number", nullable = true)
    public Integer getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(Integer unitNumber) {
        this.unitNumber = unitNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Buildings buildings = (Buildings) o;

        if (id != buildings.id) return false;
        if (level != buildings.level) return false;
        if (cost != buildings.cost) return false;
        if (name != null ? !name.equals(buildings.name) : buildings.name != null) return false;
        if (type != null ? !type.equals(buildings.type) : buildings.type != null) return false;
        if (income != null ? !income.equals(buildings.income) : buildings.income != null) return false;
        if (unitNumber != null ? !unitNumber.equals(buildings.unitNumber) : buildings.unitNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + level;
        result = 31 * result + cost;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (income != null ? income.hashCode() : 0);
        result = 31 * result + (unitNumber != null ? unitNumber.hashCode() : 0);
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

    @ManyToOne
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    public Units getUnit() {
        return unit;
    }

    public void setUnit(Units unit) {
        this.unit = unit;
    }

    public Buildings() {
    }

    public Buildings(String name, int level, int cost, String type, Integer income, Integer unitNumber, Race race, Units unit) {
        this.name = name;
        this.level = level;
        this.cost = cost;
        this.type = type;
        this.income = income;
        this.unitNumber = unitNumber;
        this.race = race;
        this.unit = unit;
    }

    public Buildings(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Buildings{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", cost=" + cost +
                ", type='" + type + '\'' +
                ", income=" + income +
                ", unitNumber=" + unitNumber +
                ", race=" + race +
                ", unit=" + unit +
                '}';
    }
}
