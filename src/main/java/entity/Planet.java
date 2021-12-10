package entity;
import exceptions.*;
import java.util.LinkedList;

public class Planet extends Satellite {
    private LinkedList <Satellite> satellites;

    // Construct
    public Planet(String name, String climate, LinkedList<Creatures> creatures, LinkedList<Ore> ores, LinkedList <Satellite> satellites) {
        super(name, climate, creatures, ores);
        this.satellites = satellites;
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
        tempString = "Planet " + super.getName() + " info:\n" + "Climate: " + getClimate() + "\n";
        for (int i = 0; i < getCreatures().size(); i++) {
            tempString += getCreatureByIndex(i).toString() + "\n";
        }
        for (int i = 0; i < satellites.size(); i++) {
            tempString += this.satellites.get(i).toString();
        }
        return tempString;
    }

}
