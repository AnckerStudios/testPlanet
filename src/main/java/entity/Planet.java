package entity;
import exceptions.*;

public class Planet extends Satillite{
    private Satillite[] satillites;

    // Construct
    public Planet(String name, String climate, Creatures[] createrus, Ore[] ores, Satillite[] satillites) {
        super(name, climate, createrus, ores);
        this.satillites = satillites;
    }

    // Set n' Get
    public Satillite[] getSatellites() {
        return satillites;
    }
    public void setSatellites(Satillite[] satillites) {
        this.satillites = satillites;
    }

    // Methods
    public Satillite getSatelliteById(int id){
        if(id < 0 && id > satillites.length){
            throw new SatelliteIdOutOfBoundsException();
        }
        return satillites[id];
    }

    public void setPlanetById(int id, Satillite data){
        if(id < 0 && id > satillites.length){
            throw new SatelliteIdOutOfBoundsException();
        }
        satillites[id] = data;
    }

    public void addSatellite(Satillite data){
        Satillite[] newArray = new Satillite[satillites.length + 1];
        System.arraycopy(satillites, 0, newArray, 0, data.getId());
        newArray[data.getId()] = data;
        System.arraycopy(satillites, data.getId(), newArray, data.getId() + 1, satillites.length - data.getId());
        satillites = new Planet[satillites.length+1];
        satillites = newArray;
    }

    public void delSatelliteById(int id) {
        if(id < 0 && id > satillites.length){
            throw new SatelliteIdOutOfBoundsException();
        }
        Creatures[] newArray = new Creatures[satillites.length];
        System.arraycopy(satillites, 0, newArray, 0, newArray.length);
        satillites = new Satillite[newArray.length - 1];
        if (id == 0) {
            System.arraycopy(newArray, 1, satillites, 0, satillites.length);
        }
        else if (id == newArray.length) {
            System.arraycopy(newArray, 0, satillites, 0, satillites.length);
        }
        else {
            System.arraycopy(newArray, 0, satillites, 0, id);
            System.arraycopy(newArray, id + 1, satillites, id, satillites.length - id);
        }
    }

    @Override
    public String toString() {
        String tempString;
        tempString = "Planet " + super.getName() + " info:\n" + "Climate: " + getClimate() + "\n";
        for (int i = 0; i < getCreatures().length; i++) {
            tempString += getCreatureByNum(i).toString() + "\n";
        }
        for (int i = 0; i < satillites.length; i++) {
            tempString += this.satillites[i].toString();
        }
        return tempString;
    }

}
