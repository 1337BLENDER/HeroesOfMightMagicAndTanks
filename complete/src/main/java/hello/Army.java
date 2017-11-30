package hello;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="army")
public class Army {
    private int id;
    private int power;
    private Collection<UnitsInArmy> units;

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator = "army_id_seq")
    @SequenceGenerator(name="army_id_seq",sequenceName = "army_id_seq")
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "power", nullable = false)
    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Army army = (Army) o;

        if (id != army.id) return false;
        if (power != army.power) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + power;
        return result;
    }

    @OneToMany(mappedBy = "army")
    public Collection<UnitsInArmy> getUnits() {
        return units;
    }

    public void setUnits(Collection<UnitsInArmy> units) {

        this.units = units;
    }

    public Army() {
    }

    public void calculatePower(){
        power=0;
        if (units.size()>0)
        for (UnitsInArmy unitsInArmy:units){
            power+=unitsInArmy.getUnit().getPower()*unitsInArmy.getNumber();
        }
    }

    public Army(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String ret="Army{" +
                "id=" + id +
                ", power=" + power +
                ", units=";
                for(UnitsInArmy unitsInArmy:units){
                    ret+=" "+unitsInArmy.getNumber()+"x"+unitsInArmy.getUnit().toString();
                }
                ret+='}';
        return ret;
    }
}
