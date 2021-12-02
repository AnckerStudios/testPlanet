package entity;

public class Planet extends Satillite{
    private Satillite[] satillites;
    public Planet(String name, Ore[] ores, Satillite[] satillites) {
        super(name, ores);
        this.satillites = satillites;
    }

    public Satillite[] getSatillites() {
        return satillites;
    }

    public void setSatillites(Satillite[] satillites) {
        this.satillites = satillites;
    }
}
