package hello;

public class Leader {
    private String name;
    private Double winrate;

    public Leader(String name, Double winrate) {
        this.name = name;
        this.winrate = winrate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWinrate() {
        return winrate;
    }

    public void setWinrate(Double winrate) {
        this.winrate = winrate;
    }
}
