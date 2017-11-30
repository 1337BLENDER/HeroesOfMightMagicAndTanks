package hello;

import javax.persistence.*;

@Entity
@Table(name = "characters_abilities", schema = "public", catalog = "postgres")
public class CharactersAbilities {
    private int id;
    private Characters character;
    private Abilities ability;

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
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

        CharactersAbilities that = (CharactersAbilities) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "character_id", referencedColumnName = "id", nullable = false)
    public Characters getCharacter() {
        return character;
    }

    public void setCharacter(Characters character) {
        this.character = character;
    }

    @ManyToOne
    @JoinColumn(name = "ability_id", referencedColumnName = "id", nullable = false)
    public Abilities getAbility() {
        return ability;
    }

    public void setAbility(Abilities ability) {
        this.ability = ability;
    }

    public CharactersAbilities() {
    }

    public CharactersAbilities(Characters character, Abilities ability) {
        this.character = character;
        this.ability = ability;
    }

    public CharactersAbilities(int id) {
        this.id = id;
    }
}
