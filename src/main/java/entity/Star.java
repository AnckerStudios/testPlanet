package entity;

import interfaces.CentralObject;

public class Star implements CentralObject {
    private int id;
    private String name;
    public Star() {
        this.name = "1";
    }
    public Star(String name) {
        this.name = name;
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
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append(this.name);
        return sb.toString();
    }
}
