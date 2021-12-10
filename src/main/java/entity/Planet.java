package entity;

public class Planet extends Satillite{
    private Satillite[] satillites;

    public Planet() {
        super();
        this.satillites = new Satillite[1];
    }
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
    public void delSatelliteById(int id) {
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
        sb.append(tab+"\tСпутники : \n");
        for(Satillite s : satillites)
            sb.append(s.toString(offset+2));
        return sb.toString();
    }
}
