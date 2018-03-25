package hello.lightEntities;

import hello.Units;

public class MediumUnitsInArmy {
    private int count;
    private Units unit;

    public MediumUnitsInArmy(int count, Units unit) {
        this.count = count;
        this.unit = unit;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Units getUnit() {
        return unit;
    }

    public void setUnit(Units unit) {
        this.unit = unit;
    }
}
