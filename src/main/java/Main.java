import entity.*;
import org.apache.commons.io.FileUtils;
import org.yaml.snakeyaml.*;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.*;
import java.lang.runtime.ObjectMethods;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // я тут чет черканул
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<PlanetSystem> planetSystems = new ArrayList();
        planetSystems.add( new PlanetSystem(
                "testSystem",
                new Star("sun"),
                new Satillite[]{
                        new Planet(
                                "Earth",
                                new Ore[]{
                                        new Ore("Цинк", 21),
                                        new Ore("Олово", 50)},
                                new Satillite[]{
                                        new Satillite("Moon", new Ore[]{
                                                new Ore("Цинк", 21)})}),
                        new Satillite(
                                "D12",
                                new Ore[]{
                                        new Ore("Медь", 21)})
                }));

        UI.menu();

        /*try {
            PrintWriter writer = new PrintWriter(new File("planet.yml"));
            Yaml yaml = new Yaml();
            yaml.dump(ps,writer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
    }
}
