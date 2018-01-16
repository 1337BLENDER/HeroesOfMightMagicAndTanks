package hello;

import javax.persistence.*;

@Entity
@Table(name = "units")
public class Units {
    private int id;
    private String name;
    private int speed;
    private String type;
    private int damage;
    private Integer range;
    private Integer ammo;
    private int cost;
    private int power;
    private int hitpoints;
    private Race race;
    private String iconUrl;
    private String battleIconUrl;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO,generator = "unitSeq")
    @SequenceGenerator(name = "unitSeq",sequenceName = "unitSeq")
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
    @Column(name = "speed", nullable = false)
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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
    @Column(name = "damage", nullable = false)
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Basic
    @Column(name = "range", nullable = true)
    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    @Basic
    @Column(name = "ammo", nullable = true)
    public Integer getAmmo() {
        return ammo;
    }

    public void setAmmo(Integer ammo) {
        this.ammo = ammo;
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
    @Column(name = "power", nullable = false)
    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Basic
    @Column(name = "hitpoints", nullable = false)
    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    @Basic
    @Column(name = "iconUrl", nullable = false)
    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Basic
    @Column(name = "battleIconUrl", nullable = false)
    public String getBattleIconUrl() {
        return battleIconUrl;
    }

    public void setBattleIconUrl(String battleIconUrl) {
        this.battleIconUrl = battleIconUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Units units = (Units) o;

        if (id != units.id) return false;
        if (speed != units.speed) return false;
        if (damage != units.damage) return false;
        if (cost != units.cost) return false;
        if (power != units.power) return false;
        if (hitpoints != units.hitpoints) return false;
        if (name != null ? !name.equals(units.name) : units.name != null) return false;
        if (type != null ? !type.equals(units.type) : units.type != null) return false;
        if (range != null ? !range.equals(units.range) : units.range != null) return false;
        if (ammo != null ? !ammo.equals(units.ammo) : units.ammo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + speed;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + damage;
        result = 31 * result + (range != null ? range.hashCode() : 0);
        result = 31 * result + (ammo != null ? ammo.hashCode() : 0);
        result = 31 * result + cost;
        result = 31 * result + power;
        result = 31 * result + hitpoints;
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

    public Units() {
    }

    public Units(String name, int speed, String type, int damage, Integer range, Integer ammo, int cost, int power, int hitpoints, Race race, String iconUrl, String battleIconUrl) {
        this.name = name;
        this.speed = speed;
        this.type = type;
        this.damage = damage;
        this.range = range;
        this.ammo = ammo;
        this.cost = cost;
        this.power = power;
        this.hitpoints = hitpoints;
        this.race = race;
        this.iconUrl = iconUrl;
        this.battleIconUrl = battleIconUrl;
    }



    public String toString(){
        return String.format("Unit[id=%d,name=%s,speed=%d,type=%s,damage=%d,range=%d,ammo=%d,cost=%d,power=%d,hitpoints=%d,Race=%s,iconUrl=%s,battleIconUrl=%s]",id,name,speed,type,damage,range==null?-1:range.intValue(),ammo==null?-1:ammo.intValue(),cost,power,hitpoints,race==null?"error":race.getName(),iconUrl,battleIconUrl);
    }
}
