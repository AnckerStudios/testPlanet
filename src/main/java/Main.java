import entity.*;
import interfaces.CentralObject;
import org.yaml.snakeyaml.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
       /* PlanetSystem ps = new PlanetSystem("testSystem", new Star("sun"), new Satellite[]{new Planet("Earth", "Normal", new Creatures[]{}, new Ore[]{}, new Satellite[]{})});
        try {
            PrintWriter writer = new PrintWriter(new File("planet.yml"));
            Yaml yaml = new Yaml();
            yaml.dump(ps, writer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

        // Methods test
        LinkedList<Creatures> creaturesList = new LinkedList<>();
        creaturesList.add(new Creatures("Wolf", "Predator"));
        creaturesList.add(new Creatures("Elephant", "Herbivory"));

        LinkedList<Ore> oreList = new LinkedList<>();
        oreList.add(new Ore("Emerald", 12));
        oreList.add(new Ore("Iron", 23));
        Satellite satellite = new Satellite("Weow", "Tropical", creaturesList, oreList);
        System.out.println(satellite);

        LinkedList<Satellite> sList = new LinkedList<>();
        sList.add(new Satellite("Exot", "Tropical", creaturesList, oreList));
        sList.add(new Satellite("Synth", "Tropical", creaturesList, oreList));
        sList.add(new Satellite("Roland", "Tropical", creaturesList, oreList));

        LinkedList<Ore> oreList1 = new LinkedList<>();
        oreList1.add(new Ore("Diamonds", 2));
        oreList1.add(new Ore("Platinum", 4));
        Planet planet = new Planet("Squire", "Tropical", creaturesList, oreList1, sList);
        System.out.println(planet);

        System.out.println("Add a new ore 'Titan' by 0 num to satellite: ");
        Ore newOre = new Ore("Titan", 11);
        satellite.addOre(newOre);
        System.out.println(satellite);
        /*System.out.println("Delete ore 'Emerald' from satellite: ");
        satellite.delOreByIndex(0);*/
        System.out.println(satellite);
        System.out.println("Sorted ores: ");
        for (int i = 0; i < satellite.getTotalAmountOfOre(); i++) {
            System.out.println("\t" + satellite.getSortedOreByQuantity().get(i).getName() + " | " + satellite.getSortedOreByQuantity().get(i).getQuantity());
        }
        Planet planet1 = new Planet("G&L", "Polar", creaturesList, oreList1);
        CentralObject cO = new Star("Unix");
        LinkedList <Satellite> pL = new LinkedList<>();
        pL.add(planet);
        pL.add(planet1);
        PlanetSystem pS = new PlanetSystem("Origin", cO, pL);
        System.out.println(pS.getPlanetByName("G&L"));


        planet.delSatelliteById(1);
        System.out.println(planet);

        Planet planet2 = new Planet("Berhinger", "Ocean", creaturesList, oreList);
        pS.addPlanet(planet2);
        System.out.println(pS);

    }
}
