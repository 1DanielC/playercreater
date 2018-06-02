package main.java.db;

import main.java.objects.Skill;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Daniel on 5/28/2018.
 */
public class PlayerDB {
    private static String TABLE = "PLAYER";
    private static String SEQUENCE = "PLAYER_SEQ";

    public int id;
    public String name;
    public String player_name;
    public String race;
    public String player_class;
    public String player_size;
    public String gender;
    public int height;
    public int weight;
    public String hair;
    public String eyes;
    public String skin;
    public String age;
    public String alignment;

    public PlayerDB() {
    }

    protected static int generateId(Connection con) {
        try {
            PreparedStatement statement = con.prepareStatement(String.format("SELECT %s.nextval id from dual", SEQUENCE));
            statement.execute();
            ResultSet results = statement.getResultSet();
            results.next();
            return results.getInt("id");
        } catch(SQLException e){
            System.err.println("Error getting new ID from player");
        }
        return -1;
    }

    public boolean isValid(){
        return id != -1;
    }

    protected void insertPlayer(Connection con) throws SQLException {
        if(!isValid()){
            System.err.println(String.format("Cannot add to %s; invalid id", TABLE));
            return;
        }
        List<String> fields = getFields();
        List<String> values = getValues();
        String insertStatement = String.format("INSERT INTO %s (%s) VALUES (%s)", "PLAYER", String.join(", ", fields), String.join(", ", values));
        runQuery(con, insertStatement);
    }

    public List<String> getFields(){
        return Stream.of(this.getClass().getFields())
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    public List<String> getValues(){
        return Stream.of(this.getClass().getFields())
                .map(field -> {
                    try {
                        return getInsertString(field.get(this));
                    } catch (Exception e) {
                        System.err.println("Can't get all field values");
                    }
                    return "";
                })
                .collect(Collectors.toList());
    }

    public String getInsertString(Object obj){
        return obj == null ? "null" :
                obj instanceof Integer ? obj.toString() :
                        String.format("%s%s%s", "'", obj.toString(), "'");
    }

    public void runQuery(Connection con, String query) throws SQLException{
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.execute();
        } catch(SQLException e) {
            System.err.println(String.format("Problem inserting to %s", TABLE));
            throw e;
        }
    }

    public void updatePlayer(Connection con) {
        if(!isValid()){
            System.err.println(String.format("Cannot update in %s; invalid id.", TABLE));
            return;
        }
        List<String> updates = Stream.of(this.getClass().getFields())
                .map(field -> {
                    try {
                        return String.format("%s = %s", field.getName(), getInsertString(field.get(this)));
                    } catch (Exception e) {
                        System.err.println(String.format("Problem building update statement for %s", TABLE));
                        return "";
                    }
                })
                .collect(Collectors.toList());
        String updateStatement = String.format("UPDATE %s SET %s WHERE ID = %s", TABLE, String.join(", ", updates), id);
        try{
            runQuery(con, updateStatement);
        } catch(SQLException e){
            return;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getPlayerClass() {
        return player_class;
    }

    public void setPlayerClass(String _class) {
        this.player_class = _class;
    }

    public String getPlayerSize() {
        return player_size;
    }

    public void setPlayerSize(String player_size) {
        this.player_size = player_size;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getHair() {
        return hair;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    public String getEyes() {
        return eyes;
    }

    public void setEyes(String eyes) {
        this.eyes = eyes;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

}
