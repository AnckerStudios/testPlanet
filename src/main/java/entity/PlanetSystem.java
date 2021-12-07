package entity;

import exceptions.PlanetIdOutOfBoundsException;
import interfaces.CentralObject;

public class PlanetSystem {
    private String name;
    private CentralObject centralObject;
    private Satillite[] planets;

    // Construct
    public PlanetSystem(String name, CentralObject centralObject, Satillite[] satillites) {
        this.name = name;
        this.centralObject = centralObject;
        this.planets = satillites;
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
    public Satillite[] getPlanets() {
        return planets;
    }
    public void setPlanets(Satillite[] satillites) {
        this.planets = satillites;
    }

    // Methods
    public Satillite getPlanetById(int id){
        return planets[id];
    }

    public Satillite setPlanetById(int id, Satillite data) {
        return planets[id] = data;
    }

    public void addPlanet(Satillite data){
        Satillite[] newArray = new Satillite[planets.length + 1];
        System.arraycopy(planets, 0, newArray, 0, data.getId());
        newArray[data.getId()] = data;
        System.arraycopy(planets, data.getId(), newArray, data.getId() + 1, planets.length - data.getId());
        planets = new Planet[planets.length+1];
        planets = newArray;
    }

    public void delPlanetById(int id) {
        if(id < 0 && id > planets.length){
            throw new PlanetIdOutOfBoundsException();
        }
        Creatures[] newArray = new Creatures[planets.length];
        System.arraycopy(planets, 0, newArray, 0, newArray.length);
        planets = new Satillite[newArray.length - 1];
        if (id == 0) {
            System.arraycopy(newArray, 1, planets, 0, planets.length);
        }
        else if (id == newArray.length) {
            System.arraycopy(newArray, 0, planets, 0, planets.length);
        }
        else {
            System.arraycopy(newArray, 0, planets, 0, id);
            System.arraycopy(newArray, id + 1, planets, id, planets.length - id);
        }
    }

}
