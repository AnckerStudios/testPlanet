import entity.*;
import interfaces.CentralObject;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Controller {
    private String url = "jdbc:postgresql://localhost:5432/universe";
    private String user = "postgres";
    private String pass = "111";
    private Connection conn = null;
    private Statement stat = null;

    public Controller() {

       /* try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, pass);

            stat = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }

    public void delPlanet(String planetName, PlanetSystem planetSystem) {
        try {
            stat.executeUpdate("DELETE FROM spaceobj WHERE name_sat = '" + planetName + "'");
            stat.executeUpdate("DELETE FROM satellite_ore WHERE satellite_id = '" + planetSystem.getPlanetByName(planetName).getId() + "'");
            stat.executeUpdate("DELETE FROM satellite_creatures WHERE satellite_id = '" + planetSystem.getPlanetByName(planetName).getId() + "'");
            for (Satellite s : ((Planet) planetSystem.getPlanetByName(planetName)).getSatellite()) {
                stat.executeUpdate("DELETE FROM spaceobj WHERE name_sat = '" + s.getName() + "'");
                stat.executeUpdate("DELETE FROM satellite_ore WHERE satellite_id = '" + s.getId() + "'");
                stat.executeUpdate("DELETE FROM satellite_creatures WHERE satellite_id = '" + s.getId() + "'");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        planetSystem.delPlanetByName(planetName);

    }

    public void addPlanet(Satellite planet, PlanetSystem planetSystem) {
        planetSystem.addPlanet(planet);

            if (planet instanceof Planet) {
                try {
                    addPlanetToPS(planetSystem, (Planet) planet);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    addSatelliteToPS(planetSystem, planet);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

    }

    public void delSatellite(int satelliteIndex, Planet planet) {
        try {
            stat.executeUpdate("DELETE FROM spaceobj WHERE name_sat = '" + planet.getSatelliteById(satelliteIndex).getName() + "'");
            stat.executeUpdate("DELETE FROM satellite_ore WHERE satellite_id = '" + planet.getSatelliteById(satelliteIndex).getId() + "'");
            stat.executeUpdate("DELETE FROM satellite_creatures WHERE satellite_id = '" + planet.getSatelliteById(satelliteIndex).getId() + "'");
            planet.delSatelliteById(satelliteIndex);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addSatellite(PlanetSystem planetSystem, Satellite satellite, Planet planet) {

        try {
            addSatelliteToPlanet(planetSystem, planet, satellite);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        planet.addSatellite(satellite);

    }

    public void delOre(String oreName, Satellite satellite) {
        try {
            stat.executeUpdate("DELETE FROM satellite_ore WHERE ore_id = '" + satellite.getOreByName(oreName).getId() + "'");
            satellite.delOreByName(oreName);
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void addOre(Ore ore, Satellite satellite) throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(url, user, pass);
        try {
            connection.setAutoCommit(false);
            String sql = "insert into ore values (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1,ore.getId());
            statement.setString(2,ore.getName());
            int rows = statement.executeUpdate();
            if (rows > 0)
                    System.out.println("good!");
            statement.clearParameters();
            String sql2 = "insert into object_ore values (?,?,?)";
            statement = connection.prepareStatement(sql2);
            statement.setObject(1,satellite.getId());
            statement.setObject(2,ore.getId());
            statement.setObject(3,ore.getQuantity());
            int rows2 = statement.executeUpdate();
            if (rows2 > 0)
                System.out.println("good!");
                connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            throw new SQLException();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setOre(String oreName, Ore ore, Satellite satellite) {
        satellite.setOreByName(oreName, ore);
    }

    public void delCreatures(String creaturesName, Satellite satellite) {
        try {
            stat.executeUpdate("DELETE FROM satellite_creatures WHERE creature_id = '" + satellite.getCreaturesByName(creaturesName).getId() + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        satellite.delCreatureByName(creaturesName);
    }

    public void addCreatures(Creatures creatures, Satellite satellite) throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(url, user, pass);
            try {
                connection.setAutoCommit(false);
            String sql = "insert into creature values (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1,creatures.getId());
            statement.setString(2,creatures.getName());
            statement.setString(3,creatures.getType());
            int rows = statement.executeUpdate();
            if (rows > 0)
                System.out.println("good!");
            statement.clearParameters();
            String sql2 = "insert into object_creature values (?,?)";
            statement = connection.prepareStatement(sql2);
            statement.setObject(1,satellite.getId());
            statement.setObject(2,creatures.getId());
            int rows2 = statement.executeUpdate();
            if (rows2 > 0)
                System.out.println("good!");
                connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            throw new SQLException();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setCreatures(String creaturesName, Creatures creatures, Satellite satellite) {
        satellite.setCreatureByName(creaturesName, creatures);
    }

    public void addPlanetSystem(PlanetSystem planetSystem) throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(url, user, pass);
        connection.setAutoCommit(false);
        //Savepoint savepointOne = null;

            try {
                //savepointOne = connection.setSavepoint("SavepointOne");
                String sql = "INSERT INTO planetSystem VALUES (?,?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setObject(1, planetSystem.getId());
                statement.setString(2, planetSystem.getName());
                int rows = statement.executeUpdate();
                if (rows > 0)
                    System.out.println("good!");
                connection.commit();
                try {
                    addCentralObj(planetSystem, planetSystem.getCentralObject());
                    for (Satellite s : planetSystem.getPlanets()) {
                        if (s instanceof Planet)
                            addPlanetToPS(planetSystem, (Planet) s);
                        else
                            addSatelliteToPS(planetSystem, s);
                    }
                    connection.commit();
                } catch (SQLException e) {
                    connection.rollback();
                    e.printStackTrace();
                    throw new SQLException();
                }
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
                throw new SQLException();
            } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        /*PrintWriter writer = new PrintWriter(new File(planetSystem.getName() + ".yml"));
        Yaml yaml = new Yaml();
        PlanetSystemSaveTest psst = new PlanetSystemSaveTest();
        psst.setSaveTest(planetSystem);
        System.out.println("id - "+psst.getId());
        yaml.dump(psst, writer);
        saveCentralObj(planetSystem.getCentralObject());
        for(Satellite s : planetSystem.getPlanets())
            savePlanet(s);*/
    }

    /*public void updatePlanetSystem(PlanetSystem planetSystem) throws FileNotFoundException {
        try {
            int rows = stat.executeUpdate("update planetSystem set name_ps = '" + planetSystem.getName() + "' where id_ps = '" + planetSystem.getId() + "'");
            System.out.println(rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    /*public void save(PlanetSystem planetSystem) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(planetSystem.getName() + ".yml"));
        Yaml yaml = new Yaml();
        yaml.dump(planetSystem, writer);
    }*/
    public PlanetSystem read2(String namePlanetSystem) throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(url, user, pass);
        try {
            ArrayList<Satellite> satellites = new ArrayList<>();
            String sql = "SELECT * FROM spaceobject inner join planetSystem on sys_uuid = fk_sys_uuid where sys_name = ? and fk_obj_uuid is NULL";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, namePlanetSystem);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                UUID id = (UUID) result.getObject("obj_uuid");
                System.out.println(id);
                String name = result.getString("obj_name");
                String climate = result.getString("climate");
                String discriminator = result.getString("discriminator");
                if (discriminator.equals("planet"))
                    satellites.add(new Planet(name, climate, id));
                else
                    satellites.add(new Satellite(name, climate, id));
            }
            //statement.clearParameters();
            for (Satellite s : satellites) {
                String sql2 = "select obj_name,ore_name from (select obj_uuid, ore_name from object_ore inner join ore on object_ore.ore_uuid = ore.ore_uuid) as Query inner join spaceobject on spaceObject.obj_uuid = obj_uuid where obj_name = ?";
                PreparedStatement statement2 = connection.prepareStatement(sql2);
                statement2.setString(1, s.getName());
                ResultSet oresResult = statement2.executeQuery();
                //ResultSet oresResult = stat.executeQuery("select name_sat,name_ore from (select satellite_id, name_ore from satellite_ore inner join ore on ore_id = id_ore) as Query inner join spaceobj on id_sat = satellite_id where name_sat = '" + s.getName() + "'");
                List<Ore> ores = new ArrayList<>();
                while (oresResult.next()) {
                    ores.add(new Ore(oresResult.getString("ore_name"), 1));
                }
                s.addOreArr(ores);
                String sql3 = "select obj_name,cre_name,cre_type from (select obj_uuid, cre_name,cre_type from object_creature inner join creature on object_creature.cre_uuid = creature.cre_uuid) as Query inner join spaceobject on spaceObject.obj_uuid = object_creature.obj_uuid where obj_name = ?";
                PreparedStatement statement3 = connection.prepareStatement(sql3);
                statement3.setString(1, s.getName());
                ResultSet creaturesResult = statement3.executeQuery();
                //"select name_sat,name_cre,type_cre from (select satellite_id, name_cre,type_cre from satellite_creatures inner join creature on creature_id = id_cre) as Query inner join spaceobj on id_sat = satellite_id where name_sat = '" + s.getName() + "'";
                List<Creatures> creatures = new ArrayList<>();
                while (creaturesResult.next()) {
                    creatures.add(new Creatures(creaturesResult.getString("cre_name"), creaturesResult.getString("cre_type")));
                }
                s.addCreatureArr(creatures);
                if (s instanceof Planet) {
                    ArrayList<Satellite> planetSat = new ArrayList<>();
                    System.out.println(s.getId());
                    String sql4 = "select * from spaceobject where fk_obj_uuid =?";
                    PreparedStatement statement4 = connection.prepareStatement(sql4);
                    statement4.setObject(1, s.getId());
                    ResultSet planetSatResult = statement4.executeQuery();
                    //ResultSet planetSatResult = stat.executeQuery("select * from spaceobj where fk_planet_id = " + s.getId());
                    while (planetSatResult.next()) {
                        UUID id = (UUID) planetSatResult.getObject("obj_uuid");
                        String name = planetSatResult.getString("obj_name");
                        String climate = planetSatResult.getString("climate");
                        planetSat.add(new Satellite(name, climate, id));
                    }
                    for (Satellite plSat : planetSat) {
                        String sql5 = "select obj_name,ore_name from (select obj_uuid, ore_name from object_ore inner join ore on ore_uuid = ore_uuid) as Query inner join spaceobject on obj_uuid = obj_uuid where obj_name = ?";
                        PreparedStatement statement5 = connection.prepareStatement(sql5);
                        statement5.setString(1, plSat.getName());
                        ResultSet oresSatResult = statement5.executeQuery();
                        //ResultSet oresSatResult = stat.executeQuery("select name_sat,name_ore from (select satellite_id, name_ore from satellite_ore inner join ore on ore_id = id_ore) as Query inner join spaceobj on id_sat = satellite_id where name_sat = '" + plSat.getName() + "'");
                        List<Ore> oresSat = new ArrayList<>();
                        while (oresSatResult.next()) {
                            oresSat.add(new Ore(oresSatResult.getString("ore_name"), 1));
                        }
                        plSat.addOreArr(oresSat);
                        String sql6 = "select obj_name,cre_name,cre_type from (select obj_uuid, cre_name,cre_type from object_creature inner join creature on cre_uuid = cre_uuid) as Query inner join spaceobject on obj_uuid = obj_uuid where obj_name = ?";
                        PreparedStatement statement6 = connection.prepareStatement(sql6);
                        statement6.setString(1, plSat.getName());
                        ResultSet creaturesSatResult = statement6.executeQuery();
                        //ResultSet creaturesSatResult = stat.executeQuery("select name_sat,name_cre,type_cre from (select satellite_id, name_cre,type_cre from satellite_creatures inner join creature on creature_id = id_cre) as Query inner join spaceobj on id_sat = satellite_id where name_sat = '" + plSat.getName() + "'");
                        List<Creatures> creaturesSat = new ArrayList<>();
                        while (creaturesSatResult.next()) {
                            creaturesSat.add(new Creatures(creaturesSatResult.getString("cre_name"), creaturesSatResult.getString("cre_type")));
                        }
                        plSat.addCreatureArr(creaturesSat);
                    }
                    ((Planet) s).addSatelliteArr(planetSat);
                }
            }
            CentralObject centralObject = null;
            String sql7 = "select * from centralObject inner join planetSystem on sys_uuid = fk_sys_uuid where sys_name = ?";
            PreparedStatement statement7 = connection.prepareStatement(sql7);
            statement7.setString(1, namePlanetSystem);
            ResultSet centralObjResult = statement7.executeQuery();
            //ResultSet centralObjResult = stat.executeQuery("select * from centralObject inner join planetSystem on id_ps = fk_planetsystem_id where name_ps = '" + namePlanetSystem + "'");
            if (centralObjResult.next())
                centralObject = new Star(centralObjResult.getString("central_name"));
            return new PlanetSystem(namePlanetSystem, centralObject, satellites);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*public PlanetSystem read(String namePlanetSystem) throws FileNotFoundException {
        File file = new File(namePlanetSystem + ".yml");
        PlanetSystem data = null;
        if (file.exists()) {
            InputStream inputStream = new FileInputStream(file);
            Yaml yaml = new Yaml();
            data = yaml.load(inputStream);
        }
        return data;
    }*/
    public void addSatelliteToPlanet(PlanetSystem planetSystem, Planet planet, Satellite satellite) throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(url, user, pass);
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO spaceobject VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, satellite.getId());
            statement.setString(2, satellite.getClass().getSimpleName().toLowerCase(Locale.ROOT));
            statement.setString(3, satellite.getName());
            statement.setString(4, satellite.getClimate());
            statement.setObject(5, planet.getId());
            statement.setObject(6, planetSystem.getId());
            int rows = statement.executeUpdate();
            if (rows > 0)
                System.out.println("good! 64");
            connection.commit();
            for (Ore o : satellite.getOres())
                addOre(o, satellite);
            for (Creatures c : satellite.getCreatures())
                addCreatures(c, satellite);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            throw new SQLException();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addPlanetToPS(PlanetSystem planetSystem, Planet planet) throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(url, user, pass);
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO spaceobject VALUES (?,?,?,?,null,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, planet.getId());
            statement.setString(2, planet.getClass().getSimpleName().toLowerCase(Locale.ROOT));
            statement.setString(3, planet.getName());
            statement.setString(4, planet.getClimate());
            statement.setObject(5, planetSystem.getId());
            int rows = statement.executeUpdate();
            if (rows > 0)
                System.out.println("good!");
            connection.commit();
            for (Satellite s : planet.getSatellite())
                addSatelliteToPlanet(planetSystem, planet, s);
            for (Ore o : planet.getOres()) {
                System.out.println(planet.getOres());
                addOre(o, planet);
                System.out.println("JKG");
            }
            for (Creatures c : planet.getCreatures())
                addCreatures(c, planet);
            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            throw new SQLException();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addSatelliteToPS(PlanetSystem planetSystem, Satellite planet) throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(url, user, pass);
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO spaceobject VALUES (?,?,?,?,null,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, planet.getId());
            statement.setString(2, planet.getClass().getSimpleName().toLowerCase(Locale.ROOT));
            statement.setString(3, planet.getName());
            statement.setString(4, planet.getClimate());
            statement.setObject(5, planetSystem.getId());
            int rows = statement.executeUpdate();
            if (rows > 0)
                System.out.println("good!");
            connection.commit();
            for (Ore o : planet.getOres())
                addOre(o, planet);
            for (Creatures c : planet.getCreatures())
                addCreatures(c, planet);
                connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            throw new SQLException();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addCentralObj(PlanetSystem planetSystem, CentralObject centralObject) throws SQLException {
        Connection connection = null;
            connection = DriverManager.getConnection(url, user, pass);
            try {
                connection.setAutoCommit(false);
                String sql = "INSERT INTO centralobject VALUES (?,?,?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setObject(1, centralObject.getId());
                statement.setString(2, centralObject.getName());
                statement.setObject(3, planetSystem.getId());
                int rows = statement.executeUpdate();
                if (rows > 0)
                   System.out.println("good!");
                    connection.commit();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ой sun");
                connection.rollback();
                throw new SQLException();
            } finally {
                if(connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
    }

    /* public Satellite loadPlanet(UUID namePlanet) throws FileNotFoundException {
         File file = new File("planet/"+namePlanet + ".yml");
         Satellite data = null;
         if (file.exists()) {
             InputStream inputStream = new FileInputStream(file);
             Yaml yaml = new Yaml();
             data = yaml.load(inputStream);
         }
         return data;
     }
     public ArrayList<Satellite> loadArrayPlanets(ArrayList<UUID> namePlanet) throws FileNotFoundException {
         ArrayList<Satellite> data = new ArrayList<>();
         for(UUID uu : namePlanet)
             data.add(loadPlanet(uu));
         return data;
     }*/
    public void saveCentralObj(CentralObject centralObject) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File("centralObject/" + centralObject.getId() + ".yml"));
        Yaml yaml = new Yaml();
        yaml.dump(centralObject, writer);
    }
    /*public CentralObject loadCentralObj(UUID namePlanet) throws FileNotFoundException {
        File file = new File("centralObject/"+namePlanet + ".yml");
        CentralObject data = null;
        if (file.exists()) {
            InputStream inputStream = new FileInputStream(file);
            Yaml yaml = new Yaml();
            data = yaml.load(inputStream);
        }
        return data;
    }*/
}
