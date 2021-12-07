package entity;

public class BlackHole implements CentralObject{
    private int id;
    private String name;

    // Construct
    public BlackHole(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Set n' Get
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
