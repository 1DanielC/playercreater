package main.java.objects;

import main.java.db.DBController;

import java.lang.reflect.Field;
import java.sql.SQLException;

/**
 * Created by Daniel on 6/2/2018.
 */
public class Player{
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

    private PlayerStats playerStats;
    private PlayerSkills playerSkills;

    public Player(int id){
        this.id = id;

        playerStats = PlayerStats.generate(id);
        playerSkills = PlayerSkills.generate(id);
        applyModifiersToSkills();
    }

    public static Player generate() {
        try {
            int id = DBController.generateId(Player.class);
            if(id != -1) {
                Player player = new Player(id);
                DBController.insertObject(player);
                return player;
            } else {
                System.err.println("Cannot generate Player: id is -1");
                return null;
            }
        } catch (SQLException e){
            System.err.println(String.format("Cannot generate record: %s", e.getMessage()));
            return null;
        }
    }

    public void update(){
        DBController.updateObject(this);
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

    private void applyModifiersToSkills(){
        Field[] skills = playerSkills.getClass().getFields();
        for(Field skill: skills){
            if(skill.getName().equals("id")){
                continue;
            }
            try {
                ABILITY ability = SkillMap.getAbility(skill.getName());
                Field stat = PlayerStats.class.getDeclaredField(ability.name());
                int value = stat.getInt(playerStats);
                skill.setInt(playerSkills, PlayerStats.getModifierValue(value));
                playerSkills.update();
            } catch (Exception e){
                System.err.println(String.format("Could not apply stat modifier to skills: %s", e.getMessage()));
            }
        }
    }
}
