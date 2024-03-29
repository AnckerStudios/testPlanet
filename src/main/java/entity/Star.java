package entity;

import interfaces.CentralObject;
import java.util.UUID;

public class Star implements CentralObject {
    private UUID id;
    private String name;

    // Construct
    public Star() {
        this.id = UUID.randomUUID();
        this.name = "1";
    }
    public Star(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    // Set n' Get
    public UUID getId() {
        return id;
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
