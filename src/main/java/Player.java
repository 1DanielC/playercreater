package main.java;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Daniel on 5/28/2018.
 */
public class Player {
    private static String TABLE = "PLAYER";

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

    public Player(int id){
        this.id = id;
    }

    public void insertPlayer(Connection con) {
        try {
            Field[] fields = this.getClass().getFields();
            List<String> variables = Arrays.asList(fields).stream()
                    .map(Field::getName)
                    .collect(Collectors.toList());
            List<String> values = new ArrayList<>();
            for(Field field: fields){
                Object obj = field.get(this);
                if(obj == null){
                    values.add("null");
                } else if(obj instanceof Integer){
                    values.add(obj.toString());
                } else{
                    values.add(String.format("%s%s%s", "'", obj.toString(), "'"));
                }
            }
            String insertStatement = String.format("INSERT INTO %s (%s) VALUES (%s)", "PLAYER", String.join(", ", variables), String.join(", ", values));
            PreparedStatement statement = con.prepareStatement(insertStatement);
            statement.execute();
            int c = 0;
        } catch(IllegalAccessException e){
            System.err.println("Problem getting list of variables");
        } catch(SQLException e){
            System.err.println("Problem inserting record");
        }
    }

    public void updatePlayer(Connection con){

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

    public void set_class(String _class) {
        this.player_class = _class;
    }

    public String getPlayer_size() {
        return player_size;
    }

    public void setPlayer_size(String player_size) {
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
