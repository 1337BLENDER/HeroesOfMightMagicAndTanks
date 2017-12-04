package hello;

import javax.persistence.*;

@Entity
public class Friends {
    private int id;
    private Users user1;
    private Users user2;

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

        Friends friends = (Friends) o;

        if (id != friends.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id_1", referencedColumnName = "id", nullable = false)
    public Users getUser1() {
        return user1;
    }

    public void setUser1(Users user1) {
        this.user1 = user1;
    }

    @ManyToOne
    @JoinColumn(name = "user_id_2", referencedColumnName = "id", nullable = false)
    public Users getUser2() {
        return user2;
    }

    public void setUser2(Users user2) {
        this.user2 = user2;
    }

    public Friends() {
    }

    public Friends(Users user1, Users user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public Friends(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Friends{" +
                "id=" + id +
                ", user1=" + user1 +
                ", user2=" + user2 +
                '}';
    }
}
