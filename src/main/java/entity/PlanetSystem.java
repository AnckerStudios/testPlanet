package entity;
import interfaces.CentralObject;
import java.util.ArrayList;
import java.util.UUID;

public class PlanetSystem {
    final private UUID id;
    private String name;
    private CentralObject centralObject;
    private ArrayList<Planet> planets;

    // Construct
    public PlanetSystem(String name, CentralObject centralObject, ArrayList<Planet> satellites) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.centralObject = centralObject;
        this.planets = satellites;
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
    public CentralObject getCentralObject() {
        return centralObject;
    }
    public void setCentralObject(CentralObject centralObject) {
        this.centralObject = centralObject;
    }
    public ArrayList <Planet> getPlanets() {
        return planets;
    }
    public void setPlanets(ArrayList <Planet> satellites) {
        this.planets = satellites;
    }

    // Methods
    public Satellite getPlanetById(int id){
        return planets.get(id);
    }

    public Satellite getPlanetByName(String name){

        for (int i = 0; i < planets.size(); i++) {
            if((planets.get(i).getName()).equals(name)){
                return planets.get(i);
            }
        }
        return null;
    }

    public Planet setPlanetById(int id, Planet data) {
        return planets.set(id, data);
    }

    public void addPlanet(Planet data){
       planets.add(data);
    }

    public void delPlanetById(int id) {
       planets.remove(id);
    }

    public int getAmountOfPlanets(){
        return planets.size();
    }
    
    public Satellite delPlanetByName(String name){
        for (int i = 0; i < planets.size(); i++) {
            if(planets.get(i).getName() == name){
                return planets.get(i);
            }
        }
        return null;
    }

    public void addSatelliteToPlanet(String name, Satellite data){
        for (int i = 0; i < planets.size(); i++) {
            if((planets.get(i).getName()).equals(name)){
                planets.get(i).addSatellite(data);
            }
        }
    }



    public String toString() {
        String tempString;
        tempString = "\tPlanet System " + this.getName() +" has " + getAmountOfPlanets() + "planets. Info:\n";
        for (int i = 0; i < planets.size(); i++) {
            tempString += planets.get(i).toString() + "\n";
        }
        return tempString;
    }
}
