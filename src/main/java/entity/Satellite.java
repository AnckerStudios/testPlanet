package entity;
import exceptions.*;
import java.util.LinkedList;

public class Satellite {
    private static int idSequence = 0;
    private int id;
    private String name;
    private String climate;
    private LinkedList <Creatures> creatures;
    private LinkedList <Ore> ores;
    public Satellite() {
        this.id = idSequence++;
        this.name = "1";
        this.ores = new LinkedList<>();
        this.climate = "1";
        this.creatures = new LinkedList<>();
    }
    // Construct
    public Satellite(String name, LinkedList <Ore> ores) {
        this.id = idSequence++;
        this.name = name;
        this.ores = ores;
        this.climate = "1";
        this.creatures =  new LinkedList<>();
    }
    public Satellite(String name, String climate, LinkedList<Creatures> creatures, LinkedList <Ore> ores) {
        this.id = idSequence++;
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
}
