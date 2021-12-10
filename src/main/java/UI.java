import entity.*;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.*;
import java.util.Scanner;

public class UI {
    public static void menu() throws FileNotFoundException {
        boolean openMenu = true;
        while (openMenu) {
            System.out.println("Выберите пункт меню");
            System.out.println("1 - Создать планетарную систему");
            System.out.println("2 - Просмотреть планетарную систему");
            System.out.println("3 - Выход");
            Yaml yaml;
            Scanner sc = new Scanner(System.in);
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    PlanetSystem planetSystem = readPlanetSystem(sc);
                    PrintWriter writer = new PrintWriter(new File(planetSystem.getName() + ".yml"));
                    yaml = new Yaml();
                    yaml.dump(planetSystem, writer);
                    System.out.println("Планета создана!");
                    break;
                case 2:
                    System.out.println("Введите название планетарной системы\n");
                    String namePlanetSystem = sc.nextLine();
                    File file = new File(namePlanetSystem + ".yml");
                    if (file.exists()) {
                        InputStream inputStream = new FileInputStream(file);
                        yaml = new Yaml();
                        PlanetSystem data = yaml.load(inputStream);
                        menuPlanetSystem(sc, data);
                    } else {
                        System.out.println("Такую систему еще не открыли");
                    }
                    break;
                case 3:
                    openMenu = false;
                    break;
            }
        }
    }
    public static void menuSatillite(Scanner sc, Satillite selectedSatillite){
        boolean openMenu = true;
        while (openMenu) {
            System.out.println(selectedSatillite);
            System.out.println("=========================");
            System.out.println("1 - Изменить содержание руд ");
            if(selectedSatillite instanceof Planet) {
                System.out.println("2 - Добавить спутник");
                System.out.println("3 - Удалить спутник");
                System.out.println("4 - Просмотреть спутник");
            }
            System.out.println("0 - Вернуться");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    menuOres(sc, selectedSatillite);
                    break;
                case 2:
                    if(selectedSatillite instanceof Planet) {
                        ((Planet) selectedSatillite).addSatellite(readSatillite(sc));
                    }
                    break;
                case 3:
                    if(selectedSatillite instanceof Planet) {
                        System.out.println("Введите номер спутника");
                        int number = Integer.parseInt(sc.nextLine());
                        ((Planet) selectedSatillite).delSatelliteById(number);
                    }
                    break;
                case 4:
                    if(selectedSatillite instanceof Planet) {
                        System.out.println("Введите название спутника\n");
                        String nameSatillite = sc.nextLine();
                        //menuSatillite(planet.getSatilliteByName(nameSatillite));
                        System.out.println("Пока нет");
                    }
                    break;
                case 0:
                    openMenu = false;
                    break;
            }
        }
    }
    public static void menuOres(Scanner sc, Satillite selectedSatillite){
        boolean openMenu = true;
        while (openMenu) {
            System.out.println(selectedSatillite.getOres());
            System.out.println("=========================");
            System.out.println("1 - Добавить руду ");
            System.out.println("2 - Удалить руду");
            System.out.println("3 - Изменить колличество руды");
            System.out.println("4 - Вернуться");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Пока нет");
                    //selectedSatillite.addOre(readOre());
                    break;
                case 2:
                    System.out.println("Пока нет");
                    break;
                case 3:
                    System.out.println("Пока нет");
                    break;
                case 4:
                    openMenu = false;
                    break;
            }
        }
    }
    public static void menuPlanetSystem(Scanner sc, PlanetSystem planetSystem){
        boolean openPSMenu = true;
        while(openPSMenu) {
            System.out.println(planetSystem);
            System.out.println("=========================");
            System.out.println("1 - Добавить объект");
            System.out.println("2 - Удалить объект");
            System.out.println("3 - Просмотреть объект");
            System.out.println("4 - Вернуться");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Выберит тип небесного тела");
                    System.out.println("planet / satillite");
                    String classSatillite = sc.nextLine();
                    if (classSatillite.equals("planet")) {
                        planetSystem.addPlanet(readPlanet(sc));
                    } else {
                        planetSystem.addPlanet(readSatillite(sc));
                    }
                    break;
                case 2:
                    System.out.println("Введите название небесного тела или его номер\n");
                    String nameDelPlanet = sc.nextLine();
                    if(nameDelPlanet.matches("[-+]?\\d+"))
                        planetSystem.delPlanetById(Integer.parseInt(nameDelPlanet));
                    else
                        System.out.println("по имени пока нет");
                    break;
                case 3:
                    System.out.println("Введите название небесного тела или его номер\n");
                    String nameSelectedPlanet = sc.nextLine();
                    Satillite selectedPlanet = null; //добавить получение планеты
                    menuSatillite(sc, selectedPlanet);

                    break;
                case 4:
                    openPSMenu = false;
                    break;
            }
        }
    }

    public static Star readStar (Scanner sc){
        System.out.println("Введите название звезды");
        String nameStar = sc.nextLine();
        Star star = new Star(nameStar);
        return star;
    }
    public static BlackHole readBlackHole (Scanner sc){
        System.out.println("Введите название черной дыры");
        String nameStar = sc.nextLine();
        BlackHole blackHole = new BlackHole(nameStar);
        return blackHole;
    }
    public static Planet readPlanet (Scanner sc){
        System.out.println("Введите название планеты");
        String nameSatillite = sc.nextLine();
        System.out.println("Введите кол-во руд");
        int oreSize = Integer.parseInt(sc.nextLine());
        Ore[] ores = new Ore[oreSize];
        for(int i = 0; i < oreSize; i++){
            ores[i] = readOre(sc);
        }
        System.out.println("Введите кол-во спутников");
        int satilliteSize = Integer.parseInt(sc.nextLine());
        Satillite[] satillites = new Satillite[satilliteSize];
        for(int i = 0; i < satilliteSize; i++){
            satillites[i] = readSatillite(sc);
        }
        Planet planet = new Planet(nameSatillite, ores, satillites);
        return planet;
    }
    public static Satillite readSatillite (Scanner sc){
        System.out.println("Введите название спутника");
        String nameSatillite = sc.nextLine();
        System.out.println("Введите кол-во руд");
        int oreSize = Integer.parseInt(sc.nextLine());
        Ore[] ores = new Ore[oreSize];
        for(int i = 0; i < oreSize; i++){
            ores[i] = readOre(sc);
        }
        Satillite satillite = new Satillite(nameSatillite, ores);
        return satillite;
    }
    public static PlanetSystem readPlanetSystem(Scanner sc){
        System.out.println("Введите название новой планетарной системы\n");
        String name = sc.nextLine();
        System.out.println("Выберит тип центрального объекта");
        System.out.println("star / black hole");
        String classCentralObject = sc.nextLine();
        CentralObject c;
        if(classCentralObject.equals("star")){
            c = readStar(sc);
        } else {
            c = readBlackHole(sc);
        }
        System.out.println("Введите кол-во небесных тел");
        int satilliteSize = Integer.parseInt(sc.nextLine());
        Satillite[] satillites = new Satillite[satilliteSize];
        for(int i = 0; i < satilliteSize; i++){
            System.out.println("Выберит тип небесного тела");
            System.out.println("planet / satillite");
            String classSatillite = sc.nextLine();
            if(classSatillite.equals("planet")){
                satillites[i] = readPlanet(sc);
            } else {
                satillites[i] = readSatillite(sc);
            }

        }
        return new PlanetSystem(name, c, satillites);
    }
    public static Ore readOre(Scanner sc){
        System.out.println("Введите название руды");
        String oreName = sc.nextLine();
        System.out.println("Введите её содержание");
        int oreValue = Integer.parseInt(sc.nextLine());
        return new Ore(oreName, oreValue);
    }
}
