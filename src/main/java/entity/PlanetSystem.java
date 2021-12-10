package entity;

import exceptions.PlanetIdOutOfBoundsException;
import interfaces.CentralObject;

import javax.xml.crypto.Data;
import java.util.LinkedList;

public class PlanetSystem {
    private String name;
    private CentralObject centralObject;
    private LinkedList<Satellite> planets;

    // Construct
    public PlanetSystem(String name, CentralObject centralObject, LinkedList<Satellite> satellites) {
        this.name = name;
        this.centralObject = centralObject;
        this.planets = satellites;
    }

    // Set n' Get
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
    public LinkedList <Satellite> getPlanets() {
        return planets;
    }
    public void setPlanets(LinkedList <Satellite> satellites) {
        this.planets = satellites;
    }

    // Methods
    public Satellite getPlanetById(int id){
        return planets.get(id);
    }

    public Satellite setPlanetById(int id, Satellite data) {
        return planets.set(id, data);
    }

    public void addPlanet(Satellite data){
       planets.add(data);
    }

    public void delPlanetById(int id) {
       planets.remove(id);
    }

}
