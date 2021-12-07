import entity.*;
import org.apache.commons.io.FileUtils;
import org.yaml.snakeyaml.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.runtime.ObjectMethods;

public class Main {
    public static void main(String[] args) {
        PlanetSystem ps = new PlanetSystem("testSystem", new Star("sun"), new Satillite[]{new Planet("Earth", "Normal",new Ore[]{},new Satillite[]{})});
        try {
            PrintWriter writer = new PrintWriter(new File("planet.yml"));
            Yaml yaml = new Yaml();
            yaml.dump(ps,writer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Methods test
        Ore []oreArray = new Ore[2];
        oreArray[0] = new Ore("Emerald", 12);
        oreArray[1] = new Ore("Iron", 23);
        Satillite satillite = new Satillite("Weow", "Tropical", oreArray);
        System.out.println(satillite.toString());

        Satillite []sArr = new Satillite[3];
        sArr[0] = new Satillite("Exot", "Tropical",oreArray);
        sArr[1] = new Satillite("Synth","Tropical", oreArray);
        sArr[2] = new Satillite("Roland", "Tropical",oreArray);

        Ore []oreArray1 = new Ore[2];
        oreArray1[0] = new Ore("Diamonds", 2);
        oreArray1[1] = new Ore("Platinum", 4);
        Planet planet = new Planet("Squier", "Tropical",oreArray1, sArr);
        System.out.println(planet.toString());
    }
}
