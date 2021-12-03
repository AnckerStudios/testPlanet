package entity;

public class Satillite {
    private int id;
    private String name;
    private Ore[] ores;

    public Satillite(String name, Ore[] ores) {
        this.name = name;
        this.ores = ores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ore[] getOres() {
        return ores;
    }

    public void setOres(Ore[] ores) {
        this.ores = ores;
    }

    public String toString(int offset){
        String tab = "\t";
        for(int i = 1; i < offset; i++)
            tab = tab+tab;
        StringBuffer sb = new StringBuffer();
        sb.append(tab+"Объект : " + this.name + "\n");
        sb.append(tab+"Руды : \n");
        for(Ore o : getOres())
            sb.append(tab + o.toString(offset));
        return sb.toString();
    }

}
