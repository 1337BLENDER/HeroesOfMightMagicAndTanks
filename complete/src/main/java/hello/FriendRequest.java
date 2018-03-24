package hello;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class FriendRequest {
    private int id;
    @NotNull
    private String sender;
    @NotNull
    private String receiver;
    private String message;
    private Boolean canceled;

    public Boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
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
        if (!(o instanceof FriendRequest)) return false;

        FriendRequest that = (FriendRequest) o;

        if (id != that.id) return false;
        if (!sender.equals(that.sender)) return false;
        if (!receiver.equals(that.receiver)) return false;
        return message != null ? message.equals(that.message) : that.message == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + sender.hashCode();
        result = 31 * result + receiver.hashCode();
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    public FriendRequest(String sender, String receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.canceled = false;
    }

    public FriendRequest() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
