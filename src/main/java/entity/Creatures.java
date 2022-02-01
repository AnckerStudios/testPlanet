package entity;

import java.util.UUID;

public class Creatures {
    private String name;
    private String type;
    private UUID id;
    public UUID getId(){
        return this.id;
    }



    // Construct
    public Creatures() {
        this.id = UUID.randomUUID();
        this.name = "1";
        this.type = "1";
    }
    public Creatures(String name, String type) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.type = type;
    }

    // Set n' Get
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String toString(int offset){
        String tab = "\t";
        for(int i = 1; i < offset; i++)
            tab = tab+"\t";
        StringBuilder sb = new StringBuilder();
        sb.append(tab+ this.name + " / " + this.type + "\n");
        return sb.toString();
    }
    // Methods
    @Override
    public String toString(){
        String tempString;
        tempString = "\t\tCreature: " + this.getName() + " | Type: " + this.getType();
        return tempString;
    }
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Creatures) ) return false;
        if(this.getName() == ((Creatures) obj).getName() && this.getType() == ((Creatures) obj).getType()) return true;
        else return false;
    }
    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
