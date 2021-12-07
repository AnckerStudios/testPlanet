package entity;

public class Satillite {
    private int id;
    private String name;
    private String climate;
    private Ore[] ores;

    // Construct
    public Satillite(String name, String climate, Ore[] ores) {
        this.name = name;
        this.ores = ores;
        this.climate = climate;
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
    public Ore[] getOres() {
        return ores;
    }
    public void setOres(Ore[] ores) {
        this.ores = ores;
    }
    public String getClimate() {
        return climate;
    }
    public void setClimate(String climate) {
        this.climate = climate;
    }

    // Methods
    @Override
    public String toString(){
        String tempString;
        tempString = "\tSatellite " + this.getName() + " info:\n" + "\t\tClimate: " + getClimate() + "\n";
        for (int i = 0; i < getOres().length; i++) {
            tempString += ores[i].toString() + "\n";
        }
        return tempString;
    }
}
