package entity;

public class Satillite {
    private int id;
    private String name;
    private String climate;
    private Creatures[] creatures;
    private Ore[] ores;

    // Construct
    public Satillite(String name, String climate, Creatures []creatures, Ore[] ores) {
        this.name = name;
        this.ores = ores;
        this.climate = climate;
        this.creatures = creatures;
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
    public Creatures[] getCreatures() {
        return creatures;
    }
    public void setCreatures(Creatures[] creatures) {
        this.creatures = creatures;
    }

    // Methods
    public Ore getOreByNum(int num){
        return ores[num];
    }

    public void setOreByNum(int num, Ore data){
        ores[num] = data;
    }

    public void addOreByNum(int num, Ore data){
        Ore[] newArray = new Ore[ores.length + 1];
        System.arraycopy(ores, 0, newArray, 0, num);
        newArray[num] = data;
        System.arraycopy(ores, num, newArray, num + 1, ores.length - num);
        ores = new Ore[ores.length+1];
        ores = newArray;
    }

    public Creatures getCreatureOnSatelliteByNum(int num){
        return creatures[num];
    }

    public void setCreatureOnSatelliteByNum(int num, Creatures data){
        creatures[num] = data;
    }

    public void addCreatureByNum(int num, Creatures data){
        Creatures[] newArray = new Creatures[creatures.length + 1];
        System.arraycopy(creatures, 0, newArray, 0, num);
        newArray[num] = data;
        System.arraycopy(creatures, num, newArray, num + 1, creatures.length - num);
        creatures = new Creatures[creatures.length+1];
        creatures = newArray;
    }

    public int getTotalAmountOfCreatures(){
        return creatures.length;
    }

    public int getTotalAmountOfOre(){
        return ores.length;
    }

    public void delOreByNum(int num) {
        Ore[] newArray = new Ore[ores.length - 1];
        for (int i = 0; i < newArray.length; i++) {
            if (i == id) {
                continue;
            } else if (i < id) {
                newArray[i] = ores[i];
            } else {
                newArray[i] = ores[i + 1];
            }
        }
        ores = newArray;
    }

    @Override
    public String toString(){
        String tempString;
        tempString = "\tSatellite " + this.getName() + " info:\n" + "\t\tClimate: " + getClimate() + "\n";
        for (int i = 0; i < getOres().length; i++) {
            tempString += ores[i].toString() + "\n";
        }
        for (int i = 0; i < getCreatures().length; i++) {
            tempString += creatures[i].toString() + "\n";
        }
        return tempString;
    }

}
