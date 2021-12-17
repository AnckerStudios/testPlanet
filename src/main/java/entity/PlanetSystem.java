package entity;
import interfaces.CentralObject;
import java.util.ArrayList;
import java.util.UUID;

public class PlanetSystem{
    final private UUID id;
    private String name;
    private CentralObject centralObject;
    private ArrayList<Satellite> planets;

    public PlanetSystem() {
        this.id = UUID.randomUUID();
        this.name = "1";
        this.centralObject = new Star();
        this.planets = new ArrayList<>();
    }
    // Construct
    public PlanetSystem(String name, CentralObject centralObject, ArrayList<Satellite> satellites) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.centralObject = centralObject;
        this.planets = satellites;
    }
    public PlanetSystem(UUID id, String name, CentralObject centralObject, ArrayList<Satellite> satellites) {
        this.id = id;
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
    public ArrayList <Satellite> getPlanets() {
        return planets;
    }
    public void setPlanets(ArrayList <Satellite> satellites) {
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

    public Satellite setPlanetById(int id, Satellite data) {
        return planets.set(id, data);
    }

    public void addPlanet(Satellite data){
        planets.add(data);
    }

    public void addPlanetById(int id,Satellite data){
        planets.add(id, data);
    }

    public void delPlanetById(int id) {
       planets.remove(id);
    }

    public int getAmountOfPlanets(){
        return planets.size();
    }

    public void delPlanetByName(String name){
        for (int i = 0; i < planets.size(); i++) {
            if((planets.get(i).getName()).equals(name)){
                planets.remove(i);
            }
        }
    }

    /*public void addSatelliteToPlanet(String name, Satellite data){
        for (int i = 0; i < planets.size(); i++) {
            if((planets.get(i).getName()).equals(name)){
                (Planet)(planets.get(i).addSatellite(data);
            }
        }
    }*/

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
