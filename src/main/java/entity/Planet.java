package entity;
import exceptions.*;
import java.util.LinkedList;

public class Planet extends Satellite {
    private LinkedList <Satellite> satellites;
    private static int idSequence = 0;
    private int id;
    // Construct
    public Planet(String name, String climate, LinkedList<Creatures> creatures, LinkedList<Ore> ores, LinkedList <Satellite> satellites) {
        super(name, climate, creatures, ores);
        this.id = idSequence++;
        this.satellites = satellites;
    }
    public Planet(String name, String climate, LinkedList<Creatures> creatures, LinkedList<Ore> ores) {
        super(name, climate, creatures, ores);
        this.id = idSequence++;
    }

    // Set n' Get
    public LinkedList <Satellite> getSatellites() {
        return satellites;
    }
    public void setSatellites(LinkedList <Satellite> satellites) {
        this.satellites = satellites;
    }

    // Methods
    public Satellite getSatelliteById(int id){
        if(id < 0 && id > satellites.size()){
            throw new SatelliteIdOutOfBoundsException();
        }
        return satellites.get(id);
    }

    public void setPlanetById(int id, Satellite data){
        if(id < 0 && id > satellites.size()){
            throw new SatelliteIdOutOfBoundsException();
        }
        satellites.set(id, data);
    }

    public void addSatellite(Satellite data){
        satellites.add(data);
    }

    public void delSatelliteById(int id) {
        satellites.remove(id);
    }

    @Override
    public String toString() {
        String tempString;
        tempString = "Planet " + this.getId() + " " +super.getName() + " info:\n" + "Climate: " + getClimate() + "\n";
        for (int i = 0; i < getCreatures().size(); i++) {
            tempString += getCreatureByIndex(i).toString() + "\n";
        }
        if(satellites != null) {
            for (int i = 0; i < satellites.size(); i++) {
                tempString += this.satellites.get(i).toString();
            }
        }
        return tempString;
    }

}
