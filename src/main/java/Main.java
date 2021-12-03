import entity.*;
import org.apache.commons.io.FileUtils;
import org.yaml.snakeyaml.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.runtime.ObjectMethods;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PlanetSystem ps = new PlanetSystem(
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
                });
        Scanner in = new Scanner(System.in);
        menu();
        int choice = in.nextInt();
        switch (choice){
            case 1:
                break;
            case 2:
                System.out.println("Введите название планетарной системы\n");
                //String namePS = in.nextLine();
                System.out.println(ps.toString());
                break;
            default:
                break;
        }

        try {
            PrintWriter writer = new PrintWriter(new File("planet.yml"));
            Yaml yaml = new Yaml();
            yaml.dump(ps,writer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void menu(){
        System.out.println("Выберите пункт меню");
        System.out.println("1 - Создать планетарную систему");
        System.out.println("2 - Просмотреть планетарную систему");
        System.out.println("3 - Сохранить планетарную систему");
        System.out.println("4 - Загрузить планетарную систему");
    }
}
