package entity;

public class Planet extends Satillite{
    private Satillite[] satillites;

    // Construct
    public Planet(String name, String climate, Creatures[] createrus, Ore[] ores, Satillite[] satillites) {
        super(name, climate, createrus, ores);
        this.satillites = satillites;
    }

    // Set n' Get
    public Satillite[] getSatillites() {
        return satillites;
    }
    public void setSatillites(Satillite[] satillites) {
        this.satillites = satillites;
    }

    // Methods
    public Satillite setPlanetById(int id, Satillite data){
        return satillites[id] = data;
    }

    public void addSatellite(Satillite data){
        Satillite[] newArray = new Satillite[satillites.length + 1];
        System.arraycopy(satillites, 0, newArray, 0, data.getId());
        newArray[data.getId()] = data;
        System.arraycopy(satillites, data.getId(), newArray, data.getId() + 1, satillites.length - data.getId());
        satillites = new Planet[satillites.length+1];
        satillites = newArray;
    }

    /* public void delSatelliteById(int id) {
        Satillite[] newArray = new Satillite[satillites.length - 1];
        for (int i = 0; i < newArray.length; i++) {
            if (i == id) {
                continue;
            } else if (i < id) {
                newArray[i] = satillites[i];
            } else {
                newArray[i] = satillites[i + 1];
            }
        }
        satillites = newArray;
    }*/

    @Override
    public String toString() {
        String tempString;
        tempString = "Planet " + super.getName() + " info:\n" + "Climate: " + getClimate() + "\n";
        for (int i = 0; i < getCreatures().length; i++) {
            tempString += getCreatureOnSatelliteByNum(i).toString() + "\n";
        }
        for (int i = 0; i < satillites.length; i++) {
            tempString += satillites[i].toString();
        }
        return tempString;
    }


}
