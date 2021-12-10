package entity;

public class PlanetSystem {
    private String name;
    private CentralObject centralObject;
    private Satillite[] planetArray;

    public PlanetSystem() {
        this.name = "1";
        this.centralObject = centralObject;
        this.planetArray = planetArray;
    }
    public PlanetSystem(String name, CentralObject centralObject, Satillite[] planetArray) {
        this.name = name;
        this.centralObject = centralObject;
        this.planetArray = planetArray;
    }

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

    public Satillite[] getSatillites() {
        return planetArray;
    }

    public void setSatillites(Satillite[] satillites) {
        this.planetArray = satillites;
    }
}
