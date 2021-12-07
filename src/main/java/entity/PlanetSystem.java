package entity;

public class PlanetSystem {
    private String name;
    private CentralObject centralObject;
    private Satillite[] planetArray;

    // Construct
    public PlanetSystem(String name, CentralObject centralObject, Satillite[] satillites) {
        this.name = name;
        this.centralObject = centralObject;
        this.planetArray = satillites;
    }

    // Set n' Get
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public CentralObject getCentralObject() {
        return centralObject;
    }
    public void setCentralObject(CentralObject centralObject) {
        this.centralObject = centralObject;
    }
    public Satillite[] getPlanetArray() {
        return planetArray;
    }
    public void setPlanetArray(Satillite[] satillites) {
        this.planetArray = satillites;
    }

    // Methods
    public Satillite getPlanetById(int id){
        return planetArray[id];
    }

    public Satillite setPlanetById(int id, Satillite data) {
        return planetArray[id] = data;
    }

    public void addPlanet(Satillite data){
        Satillite[] newArray = new Satillite[planetArray.length + 1];
        System.arraycopy(planetArray, 0, newArray, 0, data.getId());
        newArray[data.getId()] = data;
        System.arraycopy(planetArray, data.getId(), newArray, data.getId() + 1, planetArray.length - data.getId());
        planetArray = new Planet[planetArray.length+1];
        planetArray = newArray;
    }
    public void delPlanetById(int id) {
        Satillite[] newArray = new Satillite[planetArray.length - 1];
        for (int i = 0; i < newArray.length; i++) {
            if(i == id){
                continue;
            }
            else if (i < id){
                newArray[i] = planetArray[i];
            }
            else{
                newArray[i] = planetArray[i + 1];
            }
        }
        planetArray = newArray;
    }


}
