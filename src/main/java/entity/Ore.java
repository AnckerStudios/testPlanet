package entity;

public class Ore {
    private String name;
    private int quantity;

    // Construct
    public Ore(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    // Set n' Get
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
        if(quantity <= 0){
            throw new IndexOutOfBoundsException();
        }
        else {
            this.quantity = quantity;
        }
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
