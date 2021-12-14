package entity;
import interfaces.CentralObject;

import java.util.LinkedList;

public class PlanetSystem {
    private String name;
    private CentralObject centralObject;
    private LinkedList<Satellite> planets;

    public PlanetSystem() {
        this.name = "1";
        this.centralObject = new Star();
        this.planets = new LinkedList<>();
    }
    public PlanetSystem(String name, CentralObject centralObject, LinkedList<Satellite> planet) {
        this.name = name;
        this.centralObject = centralObject;
        this.planets = planet;
    }

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

    public Satellite getPlanetByName(String name){
        Satellite satellite = null;
        for (int i = 0; i < planets.size(); i++) {
            if(planets.get(i).getName().equals(name)){
                satellite = planets.get(i);
            }
        }
        return satellite;
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

    public int getAmountOfPlanets(){
        return planets.size();
    }
    public Satellite delPlanetByName(String name) {
        for (int i = 0; i < planets.size(); i++) {
            if (planets.get(i).getName() == name) {
                return planets.get(i);
            }
        }
        return null;
    }


    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("Название PS: " + this.name + "\n");
        sb.append("Центральный объект: " + this.centralObject + "\n");
        sb.append("Спутники: \n");
        for(Satellite s : planets)
            sb.append(s.toString(1)+"\n");
        return sb.toString();
    }


}
