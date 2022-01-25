package entity;
import exceptions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Planet extends Satellite {
    private List<Satellite> satellites;
    final private UUID id;

    // Construct
    public Planet() {
        super();
        this.id = UUID.randomUUID();
        this.satellites = new ArrayList<>();
    }
    public Planet(String name, String climate, int intId) {
        super(name, climate, intId);
        this.id = UUID.randomUUID();
        this.satellites = new ArrayList<>();
    }
    public Planet(String name, String climate, List<Creatures> creatures, List<Ore> ores, ArrayList<Satellite> satellites, int radius) {
        super(name, climate, creatures, ores, radius);
        this.id = UUID.randomUUID();
        this.satellites = satellites;
    }
    public Planet(String name, String climate, List<Creatures> creatures, List<Ore> ores, int radius) {
        super(name, climate, creatures, ores, radius);
        this.id = UUID.randomUUID();
    }

    // Set n' Get
    public List <Satellite> getSatellites() {
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
    public Satellite getSatelliteByName(String name){
        for (int i = 0; i < satellites.size(); i++) {
            if(satellites.get(i).getName().equals(name)){
                return satellites.get(i);
            }
        }
        return null;
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
    public void addSatelliteById(int id,Satellite data){
        satellites.add(id,data);
    }
    public void delSatelliteById(int id) {
        satellites.remove(id);
    }
    public void delSatelliteByName(String name){
        for (int i = 0; i < satellites.size(); i++) {
            if(satellites.get(i).getName().equals(name)){
                satellites.remove(i);
            }
        }
    }
    public String toString(int offset){
        String tab = "\t";
        for(int i = 1; i < offset; i++)
            tab = tab+"\t";
        StringBuffer sb = new StringBuffer();
        sb.append(tab+"Объект : " + getName() + "\n");
        sb.append(tab+"\tРуды : \n");
        for(Ore o : getOres())
            sb.append(o.toString(offset+2));
        sb.append(tab+"\tЖивность : \n");
        for(Creatures o : getCreatures())
            sb.append(o.toString(offset+2));
        sb.append(tab+"\tСпутники : \n");
        for(Satellite s : satellites)
            sb.append(s.toString(offset+2));
        return sb.toString();
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
