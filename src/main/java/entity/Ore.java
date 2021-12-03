package entity;

public class Ore {
    private String name;
    private int quantity;

    public Ore(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String toString(int offset){
        String tab = "\t";
        for(int i = 1; i < offset; i++)
            tab = tab+tab;
        StringBuffer sb = new StringBuffer();
        sb.append(tab+ this.name + " / " + this.quantity + "\n");
        return sb.toString();
    }
}
