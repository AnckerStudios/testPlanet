import entity.*;
import org.apache.commons.io.FileUtils;
import org.yaml.snakeyaml.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.runtime.ObjectMethods;

public class Main {
    public static void main(String[] args) {
        PlanetSystem ps = new PlanetSystem("testSystem", new Star("sun"), new Satillite[]{new Planet("Earth", new Ore[]{},new Satillite[]{})});
        try {
            PrintWriter writer = new PrintWriter(new File("planet.yml"));
            Yaml yaml = new Yaml();
            yaml.dump(ps,writer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
