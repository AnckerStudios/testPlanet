package entity;

public class Satillite {
    private Long id;
    private String name;
    private Ore[] ores;

    public Satillite(String name, Ore[] ores) {
        this.name = name;
        this.ores = ores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ore[] getOres() {
        return ores;
    }

    public void setOres(Ore[] ores) {
        this.ores = ores;
    }
}
