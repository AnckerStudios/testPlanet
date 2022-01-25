import entity.*;
import interfaces.CentralObject;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class UI {
    public static void menu() throws FileNotFoundException {
        final Controller controller = new Controller();
        final Scanner sc = new Scanner(System.in);
        boolean openMenu = true;
        while (openMenu) {
            System.out.println("Выберите пункт меню");
            System.out.println("1 - Создать планетарную систему");
            System.out.println("2 - Просмотреть планетарную систему");
            System.out.println("0 - Выход");
            switch (sc.nextLine()) {
                case "1":
                    controller.addPlanetSystem(readPlanetSystem(sc));
                    break;
                case "2":
                    System.out.println("Введите название планетарной системы\n");
                    menuPlanetSystem(sc, controller, controller.read2(sc.nextLine()));
                    break;
                case "0":
                    openMenu = false;
                    break;
            }
        }
    }

    public static void menuPlanetSystem(Scanner sc, Controller controller, PlanetSystem planetSystem) throws FileNotFoundException {
        while(true) {
            System.out.println(planetSystem);
            System.out.println("=========================");
            System.out.println("1 - Добавить объект");
            System.out.println("2 - Удалить объект");
            System.out.println("3 - Просмотреть объект");
            System.out.println("0 - Вернуться");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    controller.addPlanet(readSatelliteType(sc), planetSystem);
                    break;
                case 2:
                    System.out.println("Введите название небесного тела\n");
                    controller.delPlanet(sc.nextLine(), planetSystem);
                    break;
                case 3:
                    System.out.println("Введите название небесного тела или его номер\n");
                    menuSatillite(sc,controller,planetSystem ,planetSystem.getPlanetByName(sc.nextLine()));
                    break;
                case 0:
                    //controller.save2(planetSystem);
                    return;
            }
        }
    }
    public static void menuSatillite(Scanner sc, Controller controller, PlanetSystem planetSystem,Satellite selectedSatillite){
        while (true) {
            System.out.println(selectedSatillite.toString(1));
            System.out.println("=========================");
            System.out.println("1 - Просмотреть руды ");
            System.out.println("2 - Просмотреть живность ");
            if(selectedSatillite instanceof Planet) {
                System.out.println("3 - Добавить спутник");
                System.out.println("4 - Удалить спутник");
                System.out.println("5 - Просмотреть спутник");
            }
            System.out.println("0 - Вернуться");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    menuOres(sc,controller, selectedSatillite);
                    break;
                case 2:
                    menuCreatures(sc,controller, selectedSatillite);
                    break;
                case 3:
                    if(selectedSatillite instanceof Planet)
                        controller.addSatellite(planetSystem, readSatillite(sc), (Planet) selectedSatillite);
                    break;
                case 4:
                    if(selectedSatillite instanceof Planet) {
                        System.out.println("Введите номер спутника");
                        controller.delSatellite(Integer.parseInt(sc.nextLine()), (Planet) selectedSatillite);
                    }
                    break;
                case 5:
                    if(selectedSatillite instanceof Planet) {
                        System.out.println("Введите название спутника\n");
                        menuSatillite(sc,controller,planetSystem,((Planet) selectedSatillite).getSatelliteByName(sc.nextLine()));
                    }
                    break;
                case 0:
                    /*try {
                        controller.savePlanet(selectedSatillite);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }*/
                    return;
            }
        }
    }
    public static void menuOres(Scanner sc, Controller controller, Satellite selectedSatillite){
        while (true) {
            for(Ore o : selectedSatillite.getOres())
                System.out.println(o.toString(1));
            System.out.println("=========================");
            System.out.println("1 - Добавить руду ");
            System.out.println("2 - Удалить руду");
            //System.out.println("3 - Изменить руду");
            System.out.println("0 - Вернуться");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    controller.addOre(readOre(sc), selectedSatillite);
                    break;
                case 2:
                    System.out.println("Введите название руды");
                    controller.delOre(sc.nextLine(), selectedSatillite);
                    break;
                /*case 3:
                    System.out.println("Введите название руды");
                    controller.setOre(sc.nextLine(), readOre(sc), selectedSatillite);
                    break;*/
                case 0:
                    return;
            }
        }
    }
    public static void menuCreatures(Scanner sc, Controller controller, Satellite selectedSatillite){
        while (true) {
            for(Creatures o : selectedSatillite.getCreatures())
                System.out.println(o.toString(1));
            System.out.println("=========================");
            System.out.println("1 - Добавить живность ");
            System.out.println("2 - Удалить живность ");
            System.out.println("3 - Изменить живность ");
            System.out.println("0 - Вернуться");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    controller.addCreatures(readCreatures(sc), selectedSatillite);
                    break;
                case 2:
                    System.out.println("Введите название живности");
                    controller.delCreatures(sc.nextLine(), selectedSatillite);
                    break;
                case 3:
                    System.out.println("Введите название живности");
                    controller.setCreatures(sc.nextLine(), readCreatures(sc), selectedSatillite);
                    break;
                case 0:
                    return;
            }
        }
    }
    public static PlanetSystem readPlanetSystem(Scanner sc){
        System.out.println("Введите название новой планетарной системы\n");
        String name = sc.nextLine();
        CentralObject c = readCentralObject(sc);
        System.out.println("Введите кол-во небесных тел");
        int satilliteCount = Integer.parseInt(sc.nextLine());
        ArrayList<Satellite> satillites = new ArrayList();
        for(int i = 0; i < satilliteCount; i++){
            satillites.add(readSatelliteType(sc));
        }
        return new PlanetSystem(name, c, satillites);
    }
    public static CentralObject readCentralObject(Scanner sc){
        System.out.println("Выберит тип центрального объекта");
        System.out.println("star / black hole");
        String classCentralObject = sc.nextLine();
        if(classCentralObject.equals("star")){
            return readStar(sc);
        } else {
            return readBlackHole(sc);
        }
    }
    public static Star readStar (Scanner sc){
        System.out.println("Введите название звезды");
        String nameStar = sc.nextLine();
        return new Star(nameStar);
    }
    public static BlackHole readBlackHole (Scanner sc){
        System.out.println("Введите название черной дыры");
        String nameStar = sc.nextLine();
        return new BlackHole(nameStar);
    }
    public static Satellite readSatelliteType(Scanner sc){
        System.out.println("Выберит тип небесного тела");
        System.out.println("planet / satillite");
        String classSatillite = sc.nextLine();
        if(classSatillite.equals("planet")){
            return readPlanet(sc);
        } else {
            return readSatillite(sc);
        }
    }
    public static Satellite readSatillite (Scanner sc){
        System.out.println("Введите название спутника");
        String nameSatillite = sc.nextLine();
        System.out.println("Введите его радиус");
        int radius = Integer.parseInt(sc.nextLine());
        System.out.println("Введите его климат");
        String climate = sc.nextLine();
        System.out.println("Введите кол-во руд");
        int oreSize = Integer.parseInt(sc.nextLine());
        LinkedList<Ore> ores = new LinkedList<>();
        for(int i = 0; i < oreSize; i++){
            ores.add(readOre(sc));
        }
        System.out.println("Введите кол-во живности");
        int creaturesSize = Integer.parseInt(sc.nextLine());
        LinkedList<Creatures> creatures = new LinkedList<>();
        for(int i = 0; i < creaturesSize; i++){
            creatures.add(readCreatures(sc));
        }
        return new Satellite(nameSatillite,climate,creatures,ores, radius);
    }
    public static Planet readPlanet (Scanner sc){
        System.out.println("Введите название планеты");
        String nameSatillite = sc.nextLine();
        System.out.println("Введите его радиус");
        int radius = Integer.parseInt(sc.nextLine());
        System.out.println("Введите его климат");
        String climate = sc.nextLine();
        System.out.println("Введите кол-во руд");
        int oreSize = Integer.parseInt(sc.nextLine());
        LinkedList<Ore> ores = new LinkedList();
        for(int i = 0; i < oreSize; i++){
            ores.add(readOre(sc));
        }
        System.out.println("Введите кол-во живности");
        int creaturesSize = Integer.parseInt(sc.nextLine());
        LinkedList<Creatures> creatures = new LinkedList<>();
        for(int i = 0; i < creaturesSize; i++){
            creatures.add(readCreatures(sc));
        }
        System.out.println("Введите кол-во спутников");
        int satilliteSize = Integer.parseInt(sc.nextLine());
        ArrayList<Satellite> satillites = new ArrayList();
        for(int i = 0; i < satilliteSize; i++){
            satillites.add(readSatillite(sc));
        }
        return new Planet(nameSatillite,climate,creatures, ores, satillites, radius);
    }
    public static Ore readOre(Scanner sc){
        System.out.println("Введите название руды");
        String oreName = sc.nextLine();
        System.out.println("Введите её содержание");
        int oreValue = Integer.parseInt(sc.nextLine());
        return new Ore(oreName, oreValue);
    }
    public static Creatures readCreatures(Scanner sc){
        System.out.println("Введите название живности");
        String creatureName = sc.nextLine();
        System.out.println("Введите её тип");
        String creatureType = sc.nextLine();
        return new Creatures(creatureName, creatureType);
    }
}
