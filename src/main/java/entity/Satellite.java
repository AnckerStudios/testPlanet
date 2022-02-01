package entity;
import exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Satellite {
    final private UUID id;
    private String name;
    private String climate;
    protected List <Creatures> creatures;
    protected List <Ore> ores;
    private int radius;

    private int Id;

    public void setIdSat(int id) {
        Id = id;
    }

    public int getIdSat() {
        return this.Id;
    }



    // Construct
    public Satellite() {
        this.id = UUID.randomUUID();
        this.name = "1";
        this.ores = new ArrayList<>();
        this.climate = "1";
        this.creatures = new ArrayList<>();
        this.radius = 1;
    }
    public Satellite(String name, String climate, int intId) {
        this.Id = intId;
        this.id = UUID.randomUUID();
        this.name = name;
        this.ores = new ArrayList<>();
        this.climate = climate;
        this.creatures = new ArrayList<>();
        this.radius = 1;
    }
    public Satellite(String name, String climate, List<Creatures> creatures, List <Ore> ores, int radius) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.ores = ores;
        this.climate = climate;
        this.creatures = creatures;
        this.radius = radius;
    }

    // Set n' Get
    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List <Ore> getOres() {
        return ores;
    }
    public void setOres(List <Ore> ores) {
        this.ores = ores;
    }
    public Ore getOreByName(String name){
        for (int i = 0; i < ores.size(); i++) {
            if(ores.get(i).getName().equals(name)){
                return ores.get(i);
            }
        }
        return null;
    }

    public Creatures getCreaturesByName(String name){
        for (int i = 0; i < ores.size(); i++) {
            if(creatures.get(i).getName().equals(name)){
                return creatures.get(i);
            }
        }
        return null;
    }
    public void setOreByName(String name, Ore data){
        for (int i = 0; i < ores.size(); i++) {
            if(ores.get(i).getName().equals(name)) {
                ores.set(i, data);
            }
        }
    }

    public void setCreatureByName(String name, Creatures data){
        for (int i = 0; i < creatures.size(); i++) {
            if(creatures.get(i).getName().equals(name)) {
                creatures.set(i, data);
            }
        }
    }

    public void delOreByName(String name){
        for (int i = 0; i < ores.size(); i++) {
            if(ores.get(i).getName().equals(name)){
                ores.remove(i);
            }
        }
    }

    public void delCreatureByName(String name){
        for (int i = 0; i < creatures.size(); i++) {
            if(creatures.get(i).getName().equals(name)){
                creatures.remove(i);
            }
        }
    }
    public String getClimate() {
        return climate;
    }
    public void setClimate(String climate) {
        this.climate = climate;
    }
    public List<Creatures> getCreatures() {
        return creatures;
    }
    public void setCreatures(List<Creatures> creatures) {
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
        ores.add(data);
    }

    public void addOreArr(List<Ore> data){
        this.ores = data;
    }

    public void addCreatureArr(List<Creatures> data){
        this.creatures = data;
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
        creatures.add(data);
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

    public List<Ore> getSortedOreByQuantity(){
       List<Ore> newList;
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
    public String toString(int offset){
        String tab = "\t";
        for(int i = 1; i < offset; i++)
            tab = tab+"\t";
        StringBuffer sb = new StringBuffer();
        sb.append(tab+"Объект : " + this.name + "\n");
        sb.append(tab+"\tРуды : \n");
        for(Ore o : getOres())
            sb.append(o.toString(offset+2));
        sb.append(tab+"\tЖивность : \n");
        for(Creatures o : getCreatures())
            sb.append(o.toString(offset+2));
        return sb.toString();
    }
    @Override
    public String toString(){
        String tempString;
        tempString = "\tSatellite " + this.getName() + " info:\n" + "\t\tClimate: " + getClimate() + "\n";
        for (int i = 0; i < ores.size(); i++) {
            tempString += getOreByIndex(i).toString() + "\n";
        }
        for (int i = 0; i < creatures.size(); i++) {
            tempString += getCreatureByIndex(i).toString() + "\n";
        }
        return tempString;
    }


    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
