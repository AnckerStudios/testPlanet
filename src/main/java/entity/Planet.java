package entity;
import exceptions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Planet extends Satellite {
    private ArrayList<Satellite> satellites;
    final private UUID id;

    // Construct
    public Planet(String name, String climate, List<Creatures> creatures, List<Ore> ores, ArrayList<Satellite> satellites) {
        super(name, climate, creatures, ores);
        this.id = UUID.randomUUID();
        this.satellites = satellites;
    }
    public Planet(String name, String climate, List<Creatures> creatures, List<Ore> ores) {
        super(name, climate, creatures, ores);
        this.id = UUID.randomUUID();
    }

    // Set n' Get
    public ArrayList <Satellite> getSatellites() {
        return satellites;
    }
    public void setSatellites(ArrayList <Satellite> satellites) {
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
        for (int i = 0; i < creatures.size(); i++) {
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
