package entity;

public class PlanetSystem {
    private String name;
    private CentralObject centralObject;
    private Satillite[] satillites;

    public PlanetSystem(String name, CentralObject centralObject, Satillite[] satillites) {
        this.name = name;
        this.centralObject = centralObject;
        this.satillites = satillites;
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
        return satillites;
    }

    public void setSatillites(Satillite[] satillites) {
        this.satillites = satillites;
    }
}
