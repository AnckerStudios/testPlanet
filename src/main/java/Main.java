import entity.*;
import org.apache.commons.io.FileUtils;
import org.yaml.snakeyaml.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.runtime.ObjectMethods;

public class Main {
    public static void main(String[] args) {
        PlanetSystem ps = new PlanetSystem("testSystem", new Star("sun"), new Satillite[]{new Planet("Earth", "Normal", new Creatures[]{}, new Ore[]{}, new Satillite[]{})});
        try {
            PrintWriter writer = new PrintWriter(new File("planet.yml"));
            Yaml yaml = new Yaml();
            yaml.dump(ps, writer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Methods test
        Creatures[] anArr = new Creatures[2];
        anArr[0] = new Creatures("Wolf", "Predator");
        anArr[1] = new Creatures("Elephant", "Herbivory");

        Ore[] oreArray = new Ore[2];
        oreArray[0] = new Ore("Emerald", 12);
        oreArray[1] = new Ore("Iron", 23);
        Satillite satellite = new Satillite("Weow", "Tropical", anArr, oreArray);
        System.out.println(satellite.toString());

        Satillite[] sArr = new Satillite[3];
        sArr[0] = new Satillite("Exot", "Tropical", anArr, oreArray);
        sArr[1] = new Satillite("Synth", "Tropical", anArr, oreArray);
        sArr[2] = new Satillite("Roland", "Tropical", anArr, oreArray);

        Ore[] oreArray1 = new Ore[2];
        oreArray1[0] = new Ore("Diamonds", 2);
        oreArray1[1] = new Ore("Platinum", 4);
        Planet planet = new Planet("Squier", "Tropical", anArr, oreArray1, sArr);
        System.out.println(planet.toString());

        System.out.println("Add a new ore 'Titan' by 0 num to satellite: ");
        Ore newOre = new Ore("Titan", 11);
        satellite.addOreByNum(0, newOre);
        System.out.println(satellite.toString());
        System.out.println("Delete ore 'Emerald' from satellite: ");
        satellite.delOreByNum(1);
        System.out.println(satellite.toString());
        for (int i = 0; i < satellite.getTotalAmountOfOre(); i++) {
            System.out.println(satellite.getSortedOreByQuantity()[i].getName() + " | " + satellite.getSortedOreByQuantity()[i].getQuantity());
        }
    }
}
