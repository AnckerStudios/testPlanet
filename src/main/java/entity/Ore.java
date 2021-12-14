package entity;

public class Ore {
    private String name;
    private int quantity;

    public Ore() {
        this.name = "1";
        this.quantity = 1;
    }
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
            tab = tab+"\t";
        StringBuffer sb = new StringBuffer();
        sb.append(tab+ this.name + " / " + this.quantity + "\n");
        return sb.toString();
    }


    // Methods
    @Override
    public String toString(){
        String tempString;
        tempString = "\t\tOre: " + this.getName() + " | Quantity: " + this.getQuantity();
        return tempString;
    }
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Ore) ) return false;
        if(this.getName() == ((Ore) obj).getName() && this.getQuantity() == ((Ore) obj).getQuantity()) return true;
        else return false;
    }
    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
