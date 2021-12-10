package entity;

public class BlackHole implements CentralObject{
    private int id;
    private String name;

    public BlackHole() {
        this.name = "1";
    }
    public BlackHole(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
