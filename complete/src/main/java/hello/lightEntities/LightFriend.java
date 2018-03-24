package hello.lightEntities;

public class LightFriend {
    private String name;
    private Boolean online;
    private Boolean isFriend;
    private Boolean isDeleted;

    public LightFriend(String name, Boolean online, Boolean isFriend, Boolean isDeleted) {
        this.name = name;
        this.online = online;
        this.isFriend = isFriend;
        this.isDeleted = isDeleted;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Boolean getFriend() {
        return isFriend;
    }

    public void setFriend(Boolean friend) {
        isFriend = friend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }
}
