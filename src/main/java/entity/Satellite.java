package entity;
import exceptions.*;
import java.util.LinkedList;

public class Satellite {
    private int id;
    private String name;
    private String climate;
    private LinkedList <Creatures> creatures;
    private LinkedList <Ore> ores;

    // Construct
    public Satellite(String name, String climate, LinkedList<Creatures> creatures, LinkedList <Ore> ores) {
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
        if(id < 0){
            throw  new IndexOutOfBoundsException();
        }
        else {
            this.id = id;
        }
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LinkedList <Ore> getOres() {
        return ores;
    }
    public void setOres(LinkedList <Ore> ores) {
        this.ores = ores;
    }
    public String getClimate() {
        return climate;
    }
    public void setClimate(String climate) {
        this.climate = climate;
    }
    public LinkedList<Creatures> getCreatures() {
        return creatures;
    }
    public void setCreatures(LinkedList<Creatures> creatures) {
        this.creatures = creatures;
    }

    // Methods
    public Ore getOreByIndex(int index){
        if(index < 0 && index > getTotalAmountOfOre()){
            throw new OreNumOutOfBoundsException();
        }
        return ores.get(index);
    }

    public void setOreByIndex(int index, Ore data){
        if(index < 0 && index > getTotalAmountOfOre()){
            throw new OreNumOutOfBoundsException();
        }
        ores.set(index, data);
    }

    public void addOre(Ore data){
        ores.addLast(data);
    }

    public void delOreByIndex(int index) {
        if(index < 0 && index > getTotalAmountOfOre()){
            throw new OreNumOutOfBoundsException();
        }
        ores.remove(index);
    }

    public Creatures getCreatureByIndex(int index){
        if(index < 0 && index > getTotalAmountOfCreatures()){
            throw new CreatureNumOutOfBoundsException();
        }
        return creatures.get(index);
    }

    public void setCreatureByIndex(int index, Creatures data){
        if(index < 0 && index > getTotalAmountOfCreatures()){
            throw new CreatureNumOutOfBoundsException();
        }
        creatures.set(index, data);
    }

    public void addCreature(Creatures data){
        creatures.addLast(data);
    }

    public void delCreatureByIndex(int index) {
        if(index < 0 && index > getTotalAmountOfOre()){
            throw new CreatureNumOutOfBoundsException();
        }
        creatures.remove(index);
    }

    public int getTotalAmountOfCreatures(){
        return creatures.size();
    }

    public int getTotalAmountOfOre(){
        return ores.size();
    }

    public LinkedList<Ore> getSortedOreByQuantity(){
       LinkedList<Ore> newList;
       newList = ores;
        for (int i = 0; i < ores.size(); i++) {
            for (int j = 0; j < ores.size() - 1; j++) {
                if(ores.get(j).getQuantity() < ores.get(j + 1).getQuantity()){
                    Ore tempOre = ores.get(j);
                    ores.set(j,  ores.get(j+1));
                    ores.set(j+1, tempOre);
                }
            }
        }
       return newList;
    }

    @Override
    public String toString(){
        String tempString;
        tempString = "\tSatellite " + this.getName() + " info:\n" + "\t\tClimate: " + getClimate() + "\n";
        for (int i = 0; i < getOres().size(); i++) {
            tempString += getOreByIndex(i).toString() + "\n";
        }
        for (int i = 0; i < getCreatures().size(); i++) {
            tempString += getCreatureByIndex(i).toString() + "\n";
        }
        return tempString;
    }
}
