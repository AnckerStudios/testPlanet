import entity.*;
import org.apache.commons.io.FileUtils;
import org.yaml.snakeyaml.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.runtime.ObjectMethods;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
        Scanner in = new Scanner(System.in);
        menu();
        int choice = Integer.parseInt(in.nextLine());
        switch (choice){
            case 1:
                PlanetSystem planetSystem = readPlanetSystem(in);
                System.out.println(planetSystem.toString());
                break;
            case 2:
                System.out.println("Введите название планетарной системы\n");
                String namePlanetSystem = in.nextLine();
                PlanetSystem ps;
                int i = 0;
                int index = 0;
                boolean exPS = true;
                while (i< planetSystems.size() && exPS){
                    if(planetSystems.get(i).getName().equals(namePlanetSystem)) {
                        index = i;
                        exPS = false;
                    }
                    i++;
                }
                ps = planetSystems.get(index);
                System.out.println(ps.toString());
                break;
            default:
                break;
        }

        /*try {
            PrintWriter writer = new PrintWriter(new File("planet.yml"));
            Yaml yaml = new Yaml();
            yaml.dump(ps,writer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
    }
    public static void menu(){
        System.out.println("Выберите пункт меню");
        System.out.println("1 - Создать планетарную систему");
        System.out.println("2 - Просмотреть планетарную систему");
        System.out.println("3 - Сохранить планетарную систему");
        System.out.println("4 - Загрузить планетарную систему");
    }
    public static void menu2(){
        System.out.println("Выберите пункт меню");
        System.out.println("1 - Добавить объект");
        System.out.println("2 - Удалить объект");
        System.out.println("3 - Изменить объект");
        System.out.println("3 - Сохранить планетарную систему");
        System.out.println("4 - Загрузить планетарную систему");
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
            System.out.println("Введите название руды");
            String oreName = sc.nextLine();
            System.out.println("Введите её содержание");
            int oreValue = Integer.parseInt(sc.nextLine());
            ores[i] = new Ore(oreName, oreValue);
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
            System.out.println("Введите название руды");
            String oreName = sc.nextLine();
            System.out.println("Введите её содержание");
            int oreValue = Integer.parseInt(sc.nextLine());
            ores[i] = new Ore(oreName, oreValue);
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

    /*public static CentralObject readCentralObject (Scanner sc, String classCO){
        System.out.println("Введите название центрального объекта");
        String nameStar = sc.nextLine();
        if(classCO.equals("star")){
            c = readStar(in);
        } else if(classCO.equals("black hole")){
            c = readBlackHole(in);
        }
        BlackHole blackHole = new BlackHole(nameStar);
        return blackHole;
    }*/
}
