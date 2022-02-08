import entity.*;
import interfaces.CentralObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class Controller {
    private static final Logger logger = LogManager.getLogger();
    private String url = "jdbc:postgresql://localhost:5432/universe";
    private String user = "postgres";
    private String pass = "111";
    private Connection connection = null;

    public Controller() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, pass);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void delPlanetToUI(String planetName, PlanetSystem planetSystem) throws SQLException {
        try {
            delObject(planetSystem.getPlanetByName(planetName).getId(),planetSystem);
            planetSystem.delPlanetByName(planetName);
            logger.debug("Planet " + planetName + "deleted successfully from " + planetSystem.getName());
        } catch (Exception e) {
            connection.rollback();
            logger.error("Error!" + e);
            throw new SQLException(e);
        }

    }
    public void delSatelliteToUI(String satelliteName, Planet planet, PlanetSystem planetSystem) throws SQLException {
        try {
            delObject(planet.getSatelliteByName(satelliteName).getId(),planetSystem);
            planet.delSatelliteByName(satelliteName);
            logger.debug("Satellite " + satelliteName + "deleted successfully from " + planet.getName());
        } catch (Exception e) {
            connection.rollback();
            logger.error("Error!" + e);
            throw new SQLException(e);
        }
    }
    public void delObject(UUID planetUuid, PlanetSystem planetSystem) throws SQLException {
        try {
            String sql = "DELETE FROM object_ore WHERE fk2_obj_uuid = ?"; //stat.executeUpdate("DELETE FROM satellite_ore WHERE satellite_id = '" + planetSystem.getPlanetByName(planetName).getId() + "'");
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1,planetUuid);
            statement.executeUpdate();
            statement.clearParameters();
            sql = "DELETE FROM object_creature WHERE fk2_obj_uuid = ?"; //stat.executeUpdate("DELETE FROM satellite_creatures WHERE satellite_id = '" + planetSystem.getPlanetByName(planetName).getId() + "'");
            statement = connection.prepareStatement(sql);
            statement.setObject(1,planetUuid);
            statement.executeUpdate();
            if(planetSystem.getPlanetById(planetUuid) != null) {
                for (Satellite s : ((Planet) planetSystem.getPlanetById(planetUuid)).getSatellite()) {
                    delObject(s.getId(), planetSystem);
                }
            }
            statement.clearParameters();
            sql = "DELETE FROM spaceobject WHERE obj_uuid = ?"; //stat.executeUpdate("DELETE FROM spaceobj WHERE name_sat = '" + planetName + "'");
            statement = connection.prepareStatement(sql);
            statement.setObject(1,planetUuid);
            statement.executeUpdate();
        } catch (Exception e) {
            connection.rollback();
            logger.error("Error!" + e);
            throw new SQLException(e);
        }

    }

    public void addPlanetToUI(Satellite planet, PlanetSystem planetSystem) throws SQLException {
        try {
            if (planet instanceof Planet) {
                addPlanetToPS(planetSystem, (Planet) planet);
                logger.debug("Planet " + planet.getName() + "added successfully to " + planetSystem.getName());
            }
            else {
                addSatelliteToPS(planetSystem, planet);
                logger.debug("Planet " + planet.getName() + "added successfully " + planetSystem.getName());
            }
            planetSystem.addPlanet(planet);
        } catch (Exception e) {
            connection.rollback();
            logger.error("Error!" + e);
            throw new SQLException(e);
        }
    }

    public void addSatelliteToUI(PlanetSystem planetSystem, Satellite satellite, Planet planet) throws SQLException {
        try {
            addSatelliteToPlanet(planetSystem, planet, satellite);
            planet.addSatellite(satellite);
        } catch (Exception e) {
            connection.rollback();
            logger.error("Error!" + e);
            throw new SQLException(e);
        }
    }

    public void delOre(String oreName, Satellite satellite) throws SQLException {
        try {
            String sql ="DELETE FROM object_ore WHERE fk2_ore_uuid = ? AND fk2_obj_uuid = ?"; //DELETE FROM satellite_ore WHERE ore_id = '" + satellite.getOreByName(oreName).getId() + "'
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1,satellite.getOreByName(oreName).getId());
            statement.setObject(2,satellite.getId());
            statement.executeUpdate();
            satellite.delOreByName(oreName);
            logger.info("Ore " + oreName + " deleted successfully from " + satellite.getName());
        } catch (Exception e) {
            connection.rollback();
            logger.error("Error!" + e);
            throw new SQLException(e);
        }
    }
    public void addOre(Ore ore, Satellite satellite) throws SQLException {
        try {
            UUID ore_uuid = null;
            String sql = "SELECT ore_uuid from ore WHERE ore_name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1,ore.getName());
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                System.out.println(result.getString("ore_uuid"));
                ore_uuid = (UUID) result.getObject("ore_uuid");
            }
            else{
                statement.clearParameters();
                String sql1 = "insert into ore values (?,?)";
                statement = connection.prepareStatement(sql1);
                statement.setObject(1,ore.getId());
                statement.setString(2,ore.getName());
                int rows = statement.executeUpdate();
                ore_uuid = ore.getId();
            }
            if(result != null)
                result.close();
            statement.clearParameters();
            String sql2 = "insert into object_ore values (?,?,?)";
            statement = connection.prepareStatement(sql2);
            statement.setObject(1,satellite.getId());
            statement.setObject(2,ore_uuid);
            statement.setObject(3,ore.getQuantity());
            int rows2 = statement.executeUpdate();
            //connection.commit();
        } catch (Exception e) {
            connection.rollback();
            logger.error("Error!" + e);
            throw new SQLException(e);
        }
    }

    public void delCreatures(String creaturesName, Satellite satellite) throws SQLException {
        try {
            String sql ="DELETE FROM object_creature WHERE fk2_cre_uuid = ? AND fk2_obj_uuid = ?"; //DELETE FROM satellite_ore WHERE ore_id = '" + satellite.getOreByName(oreName).getId() + "'
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1,satellite.getCreaturesByName(creaturesName).getId());
            statement.setObject(2,satellite.getId());
            statement.executeUpdate();
            satellite.delCreatureByName(creaturesName);
            logger.info("Creature " + creaturesName + " deleted successfully from " + satellite.getName());
        } catch (Exception e) {
            connection.rollback();
            logger.error("Error!" + e);
            throw new SQLException(e);
        }
    }

    public void addCreatures(Creatures creatures, Satellite satellite) throws SQLException {
            try {
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
            //connection.commit();
        } catch (Exception e) {
            connection.rollback();
            logger.error("Error!" + e);
            throw new SQLException(e);
        }
    }


    public void addPlanetSystem(PlanetSystem planetSystem) throws SQLException {
        try {
            String sql = "INSERT INTO planetSystem VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, planetSystem.getId());
            statement.setString(2, planetSystem.getName());
            int rows = statement.executeUpdate();
            if (rows > 0)
                System.out.println("good!");
            addCentralObj(planetSystem, planetSystem.getCentralObject());
            for (Satellite s : planetSystem.getPlanets()) {
                if (s instanceof Planet)
                    addPlanetToPS(planetSystem, (Planet) s);
                else
                    addSatelliteToPS(planetSystem, s);
            }
            //connection.commit();
        } catch (Exception e) {
            connection.rollback();
            logger.error("Error!" + e);
            throw new SQLException(e);
        }
    }
    public PlanetSystem readPlanetSystem(String name) throws SQLException {
        String sql = "SELECT sys_uuid from planetSystem where sys_name = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        ResultSet result = statement.executeQuery();
        if(result.next()){
            return new PlanetSystem((UUID) result.getObject("sys_uuid"), name, readCentralObject(name), readPlanet(name));
        }
        return null;
    }
    public CentralObject readCentralObject(String namePlanetSystem) throws SQLException {
        CentralObject centralObject = null;
        String sql7 = "select * from centralObject inner join planetSystem on sys_uuid = fk_sys_uuid where sys_name = ?";
        PreparedStatement statement7 = connection.prepareStatement(sql7);
        statement7.setString(1, namePlanetSystem);
        ResultSet centralObjResult = statement7.executeQuery();
        if (centralObjResult.next())
            centralObject = new Star(centralObjResult.getString("central_name"));
        return centralObject;
    }
    public List<Satellite> readSatellites(String nameSatellite) throws SQLException {
        List<Satellite> satellites = new ArrayList<>();
        String sql = "SELECT * FROM spaceobject WHERE fk_obj_uuid = (SELECT obj_uuid FROM spaceobject WHERE obj_name = ?)"; //SELECT * FROM spaceobject WHERE fk_obj_uuid = ?
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nameSatellite);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            UUID id = (UUID) result.getObject("obj_uuid");
            String name = result.getString("obj_name");
            String climate = result.getString("climate");
            satellites.add(new Satellite(id, name, climate, readCreatures(name), readOres(name), 1));
        }
        return satellites;
    }
    public List<Satellite> readPlanet(String namePlanetSystem) throws SQLException {
        List<Satellite> satellites = new ArrayList<>();
        String sql = "SELECT * FROM spaceobject inner join planetSystem on sys_uuid = fk_sys_uuid where sys_name = ? and fk_obj_uuid is NULL";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, namePlanetSystem);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            UUID id = (UUID) result.getObject("obj_uuid");
            String name = result.getString("obj_name");
            String climate = result.getString("climate");
            if(result.getString("discriminator").equals("planet"))
                satellites.add(new Planet(id, name, climate, readCreatures(name), readOres(name), readSatellites(name), 1));
            else
                satellites.add(new Satellite(id, name, climate, readCreatures(name), readOres(name), 1));
        }
        return satellites;
    }
    public List<Ore> readOres(String nameSatellite) throws SQLException {
        List<Ore> ores = new ArrayList<>();
        String sql = "select obj_name,ore_name, ore_uuid, ore_value from (select fk2_obj_uuid, ore_name, ore_uuid, ore_value from object_ore inner join ore on fk2_ore_uuid = ore_uuid) as Query inner join spaceobject on obj_uuid = fk2_obj_uuid where obj_name = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nameSatellite);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            ores.add(new Ore((UUID) result.getObject("ore_uuid"), result.getString("ore_name"), result.getInt("ore_value")));
        }
        return ores;
    }
    public List<Creatures> readCreatures(String nameSatellite) throws SQLException {
        List<Creatures> creatures = new ArrayList<>();
        String sql = "select obj_name,cre_name,cre_type, cre_uuid from (select fk2_obj_uuid, cre_name,cre_type, cre_uuid from object_creature inner join creature on fk2_cre_uuid = cre_uuid) as Query inner join spaceobject on obj_uuid = fk2_obj_uuid where obj_name = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nameSatellite);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            creatures.add(new Creatures((UUID) result.getObject("cre_uuid"), result.getString("cre_name"), result.getString("cre_type")));
        }
        return creatures;
    }
    public void close(){
        if(connection != null) {
            try {
                connection.commit();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("Error!" + e);
            }
        }
    }

    /*public void updatePlanetSystem(PlanetSystem planetSystem) throws FileNotFoundException {
        try {
            int rows = stat.executeUpdate("update planetSystem set name_ps = '" + planetSystem.getName() + "' where id_ps = '" + planetSystem.getId() + "'");
            System.out.println(rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public void save(PlanetSystem planetSystem) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(planetSystem.getName() + ".yml"));
        Yaml yaml = new Yaml();
        yaml.dump(planetSystem, writer);
    }*/

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
        try {
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
            for (Ore o : satellite.getOres())
                addOre(o, satellite);
            for (Creatures c : satellite.getCreatures())
                addCreatures(c, satellite);
            //connection.commit();
        } catch (Exception e) {
            connection.rollback();
            logger.error("Error!" + e);
            throw new SQLException(e);
        }
    }

    public void addPlanetToPS(PlanetSystem planetSystem, Planet planet) throws SQLException {
        try {
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
            for (Satellite s : planet.getSatellite())
                addSatelliteToPlanet(planetSystem, planet, s);
            for (Ore o : planet.getOres())
                addOre(o, planet);
            for (Creatures c : planet.getCreatures())
                addCreatures(c, planet);
            //connection.commit();
        } catch (Exception e) {
            connection.rollback();
            logger.error("Error!" + e);
            throw new SQLException(e);
        }
    }

    public void addSatelliteToPS(PlanetSystem planetSystem, Satellite planet) throws SQLException {
        try {
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
            for (Ore o : planet.getOres())
                addOre(o, planet);
            for (Creatures c : planet.getCreatures())
                addCreatures(c, planet);
            //connection.commit();
        } catch (Exception e) {
            connection.rollback();
            logger.error("Error!" + e);
            throw new SQLException(e);
        }
    }

    public void addCentralObj(PlanetSystem planetSystem, CentralObject centralObject) throws SQLException {
            try {
                String sql = "INSERT INTO centralobject VALUES (?,?,?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setObject(1, centralObject.getId());
                statement.setString(2, centralObject.getName());
                statement.setObject(3, planetSystem.getId());
                int rows = statement.executeUpdate();
                if (rows > 0)
                   System.out.println("good!");
                //connection.commit();
            } catch (Exception e) {
                connection.rollback();
                logger.error("Error!" + e);
                throw new SQLException(e); //Runtime
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
        logger.info("central object saved");
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
