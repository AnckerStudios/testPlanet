import entity.*;
import interfaces.CentralObject;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Controller {
    public void delPlanet(String planetName, PlanetSystem planetSystem){
        planetSystem.delPlanetByName(planetName);
    }
    public void addPlanet(Satellite planet, PlanetSystem planetSystem){
        planetSystem.addPlanet(planet);
    }
    public void delSatellite(int satelliteIndex, Planet planet){
        planet.delSatelliteById(satelliteIndex);
    }
    public void addSatellite(Satellite satellite, Planet planet){
        planet.addSatellite(satellite);
    }
    public void delOre(int oreIndex, Satellite satellite) {
        satellite.delOreByIndex(oreIndex);
    }
    public void addOre(Ore ore, Satellite satellite){
        satellite.addOre(ore);
    }
    public void setOre(int oreIndex,Ore ore, Satellite satellite){
        satellite.setOreByIndex(oreIndex, ore);
    }
    public void delCreatures(int creaturesIndex, Satellite satellite) {
        satellite.delCreatureByIndex(creaturesIndex);
    }
    public void addCreatures(Creatures creatures, Satellite satellite){
        satellite.addCreature(creatures);
    }
    public void setCreatures(int creaturesIndex,Creatures creatures, Satellite satellite){
        satellite.setCreatureByIndex(creaturesIndex, creatures);
    }
    public void save(PlanetSystem planetSystem) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(planetSystem.getName() + ".yml"));
        Yaml yaml = new Yaml();
        yaml.dump(planetSystem, writer);
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
}
