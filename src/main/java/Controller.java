import entity.*;
import interfaces.CentralObject;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.UUID;

public class Controller {
    /*public void add(Object o1, Object o2){
        if(o2 instanceof PlanetSystem)
            ((PlanetSystem) o2).addPlanet((Satellite) o1);
        else if(o2 instanceof Satellite){
            if(o1 instanceof Satellite)
                ((Planet) o2).addSatellite((Satellite) o1);
            if(o1 instanceof Ore)
                ((Satellite) o2).addOre((Ore) o1);
            if(o1 instanceof Creatures)
                ((Satellite) o2).addCreature((Creatures) o1);
        }
    }*/
    public void delPlanet(String planetName, PlanetSystem planetSystem){
        planetSystem.delPlanetByName(planetName);
    }
    public void addPlanet(Satellite planet, PlanetSystem planetSystem){
        planetSystem.addPlanet(planet);
        try {
            savePlanet(planet);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void delSatellite(int satelliteIndex, Planet planet){
        planet.delSatelliteById(satelliteIndex);
    }
    public void addSatellite(Satellite satellite, Planet planet){
        planet.addSatellite(satellite);
    }
    public void delOre(String oreName, Satellite satellite) {
        satellite.delOreByName(oreName);
    }
    public void addOre(Ore ore, Satellite satellite){
        satellite.addOre(ore);
    }
    public void setOre(String oreName,Ore ore, Satellite satellite){
        satellite.setOreByName(oreName, ore);
    }
    public void delCreatures(String creaturesName, Satellite satellite) {
        satellite.delCreatureByName(creaturesName);
    }
    public void addCreatures(Creatures creatures, Satellite satellite){
        satellite.addCreature(creatures);
    }
    public void setCreatures(String creaturesName,Creatures creatures, Satellite satellite){
        satellite.setCreatureByName(creaturesName, creatures);
    }
    public void save2(PlanetSystem planetSystem) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(planetSystem.getName() + ".yml"));
        Yaml yaml = new Yaml();
        PlanetSystemSaveTest psst = new PlanetSystemSaveTest();
        psst.setSaveTest(planetSystem);
        System.out.println("id - "+psst.getId());
        yaml.dump(psst, writer);
        saveCentralObj(planetSystem.getCentralObject());
        for(Satellite s : planetSystem.getPlanets())
            savePlanet(s);
    }
    public void save(PlanetSystem planetSystem) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(planetSystem.getName() + ".yml"));
        Yaml yaml = new Yaml();
        yaml.dump(planetSystem, writer);
    }
    public PlanetSystem read2(String namePlanetSystem) throws FileNotFoundException {
        File file = new File(namePlanetSystem + ".yml");
        //PlanetSystem data = null;
        PlanetSystemSaveTest psst = null;
        if (file.exists()) {
            InputStream inputStream = new FileInputStream(file);
            Yaml yaml = new Yaml();
            psst = yaml.load(inputStream);
        }
        PlanetSystem data = new PlanetSystem(psst.getId(), psst.getName() ,loadCentralObj(psst.getCentralObject()),loadArrayPlanets(psst.getPlanets()));
        return data;
    }
    public PlanetSystem read(String namePlanetSystem) throws FileNotFoundException {
        File file = new File(namePlanetSystem + ".yml");
        PlanetSystem data = null;
        if (file.exists()) {
            InputStream inputStream = new FileInputStream(file);
            Yaml yaml = new Yaml();
            data = yaml.load(inputStream);
        }
        return data;
    }
    public void savePlanet(Satellite planet) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File("planet/"+planet.getId() + ".yml"));
        Yaml yaml = new Yaml();
        yaml.dump(planet, writer);
    }
    public Satellite loadPlanet(UUID namePlanet) throws FileNotFoundException {
        File file = new File("planet/"+namePlanet + ".yml");
        Satellite data = null;
        if (file.exists()) {
            InputStream inputStream = new FileInputStream(file);
            Yaml yaml = new Yaml();
            data = yaml.load(inputStream);
        }
        return data;
    }
    public ArrayList<Satellite> loadArrayPlanets(ArrayList<UUID> namePlanet) throws FileNotFoundException {
        ArrayList<Satellite> data = new ArrayList<>();
        for(UUID uu : namePlanet)
            data.add(loadPlanet(uu));
        return data;
    }
    public void saveCentralObj(CentralObject centralObject) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File("centralObject/"+centralObject.getId() + ".yml"));
        Yaml yaml = new Yaml();
        yaml.dump(centralObject, writer);
    }
    public CentralObject loadCentralObj(UUID namePlanet) throws FileNotFoundException {
        File file = new File("centralObject/"+namePlanet + ".yml");
        CentralObject data = null;
        if (file.exists()) {
            InputStream inputStream = new FileInputStream(file);
            Yaml yaml = new Yaml();
            data = yaml.load(inputStream);
        }
        return data;
    }
}
