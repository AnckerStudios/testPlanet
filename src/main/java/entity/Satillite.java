package entity;
import exceptions.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

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
        if(num < 0 && num > getTotalAmountOfOre()){
            throw new OreNumOutOfBoundsException();
        }
        return ores[num];
    }

    public void setOreByNum(int num, Ore data){
        if(num < 0 && num > getTotalAmountOfOre()){
            throw new OreNumOutOfBoundsException();
        }
        ores[num] = data;
    }

    public void addOreByNum(int num, Ore data){
        if(num < 0 && num > getTotalAmountOfOre()){
            throw new OreNumOutOfBoundsException();
        }
        Ore[] newArray = new Ore[ores.length + 1];
        System.arraycopy(ores, 0, newArray, 0, num);
        newArray[num] = data;
        System.arraycopy(ores, num, newArray, num + 1, ores.length - num);
        ores = new Ore[ores.length + 1];
        ores = newArray;
    }

    public void delOreByNum(int num) {
        if(num < 0 && num > getTotalAmountOfOre()){
            throw new OreNumOutOfBoundsException();
        }
        Ore[] newArray = new Ore[ores.length];
        System.arraycopy(ores, 0, newArray, 0, newArray.length);
        ores = new Ore[newArray.length - 1];
        if (num == 0) {
            System.arraycopy(newArray, 1, ores, 0, ores.length);
        }
        else if (num == newArray.length) {
            System.arraycopy(newArray, 0, ores, 0, ores.length);
        }
        else {
            System.arraycopy(newArray, 0, ores, 0, num);
            System.arraycopy(newArray, num + 1, ores, num, ores.length - num);
        }
    }

    public Creatures getCreatureByNum(int num){
        if(num < 0 && num > getTotalAmountOfCreatures()){
            throw new CreatureNumOutOfBoundsException();
        }
        return creatures[num];
    }

    public void setCreatureByNum(int num, Creatures data){
        if(num < 0 && num > getTotalAmountOfCreatures()){
            throw new CreatureNumOutOfBoundsException();
        }
        creatures[num] = data;
    }

    public void addCreatureByNum(int num, Creatures data){
        if(num < 0 && num > getTotalAmountOfCreatures()){
            throw new CreatureNumOutOfBoundsException();
        }
        Creatures[] newArray = new Creatures[creatures.length + 1];
        System.arraycopy(creatures, 0, newArray, 0, num);
        newArray[num] = data;
        System.arraycopy(creatures, num, newArray, num + 1, creatures.length - num);
        creatures = new Creatures[creatures.length+1];
        creatures = newArray;
    }

    public void delCreatureByNum(int num) {
        if(num < 0 && num > getTotalAmountOfOre()){
            throw new CreatureNumOutOfBoundsException();
        }
        Creatures[] newArray = new Creatures[creatures.length];
        System.arraycopy(ores, 0, newArray, 0, newArray.length);
        ores = new Ore[newArray.length - 1];
        if (num == 0) {
            System.arraycopy(newArray, 1, ores, 0, ores.length);
        }
        else if (num == newArray.length) {
            System.arraycopy(newArray, 0, ores, 0, ores.length);
        }
        else {
            System.arraycopy(newArray, 0, ores, 0, num);
            System.arraycopy(newArray, num + 1, ores, num, ores.length - num);
        }
    }

    public int getTotalAmountOfCreatures(){
        return creatures.length;
    }

    public int getTotalAmountOfOre(){
        return ores.length;
    }

    public Ore[] getSortedOreByQuantity(){
        Ore[] sortedArray = new Ore[getTotalAmountOfOre()];
        Ore temp;
        int num = 0;
        System.arraycopy(ores, 0, sortedArray, 0, getTotalAmountOfOre());
        for (int i = 0; i < sortedArray.length - 1; i++) {
            for (int j = 0; j < sortedArray.length - 1; j++) {
                if (sortedArray[j].getQuantity() < sortedArray[j + 1].getQuantity()) {
                    temp = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = temp;
                }
            }
        }
        return sortedArray;
    }

    @Override
    public String toString(){
        String tempString;
        tempString = "\tSatellite " + this.getName() + " info:\n" + "\t\tClimate: " + getClimate() + "\n";
        for (int i = 0; i < getOres().length; i++) {
            tempString += getOreByNum(i).toString() + "\n";
        }
        for (int i = 0; i < getCreatures().length; i++) {
            tempString += getCreatureByNum(i).toString() + "\n";
        }
        return tempString;
    }
}
