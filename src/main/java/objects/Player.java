package main.java.objects;

import main.java.db.DBController;
import main.java.objects.enums.*;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 6/2/2018.
 */
public class Player{
    public int id;

    public int age;
    public String alignment;
    public String eyes;
    public String gender;
    public String hair;
    public int height;
    public String name;
    public String player_name;
    public String player_class;
    public String player_size;
    public String race;
    public String skin;
    public int weight;

    private AbilityScores abilityScores;
    private PlayerSkills playerSkills;
    private PlayerStats playerStats;
    private List<PlayerHistory> playerHistory;

    public Player(){
    }

    public Player(int id){
        this.id = id;
    }

    public int getId(){return id;}

    public static Player generate() {
        try {
            int id = DBController.generateId(Player.class);
            if(id != -1) {
                Player player = new Player(id);
                DBController.insertObject(player);
                player.abilityScores = AbilityScores.generate(id);
                player.playerSkills = PlayerSkills.generate(id);
                player.playerStats = PlayerStats.generate(id);
                player.playerHistory = new ArrayList<>();


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

    public void setAge(int age){ this.age = age; }
    public void setAlignment(ALIGNMENT alignment){ this.alignment = alignment.toString(); }
    public void setEyes(String color){ this.eyes = color; }
    public void setGender(GENDER gender){ this.gender = gender.toString(); }
    public void setHair(String color){ this.hair = color; }
    public void setHeight(int height){ this.height = height; }
    public void setName(String name){ this.name = name; }
    public void setPlayerClass(PLAYERCLASS _class){ this.player_class = _class.toString(); }
    public void setPlayerName(String name){ this.player_name = name; }
    public void setPlayerSize(SIZE size){ this.player_size = size.toString(); }
    public void setRace(RACE race){ this.race = race.toString();}
    public void setSkin(String color){ this.skin = color; }
    public void setWeight(int weight){ this.weight = weight; }

    public void setAbilityScores(AbilityScores ps){
        abilityScores = ps;}
    public void setPlayerSkills(PlayerSkills ps){playerSkills = ps;}

    public AbilityScores getAbilityScores(){return abilityScores;}
    public PlayerSkills getPlayerSkills(){return playerSkills;}

    public void setAbilityScores(int str, int dex, int con, int intel, int wis, int cha){
        abilityScores.strength = str;
        abilityScores.dexterity = dex;
        abilityScores.constitution = con;
        abilityScores.intelligence = intel;
        abilityScores.wisdom = wis;
        abilityScores.charisma = cha;
        applyModifiersToSkills();
    }

    public void setStats(){
        applyAbilitiesToStats();
    }

    public void applyAbilitiesToStats(){
        playerStats.increaseArmor_class(AbilityScores.getModifierValue(abilityScores.dexterity));
        playerStats.increaseFortitude(AbilityScores.getModifierValue(abilityScores.constitution));
        playerStats.increaseReflex(AbilityScores.getModifierValue(abilityScores.dexterity));
        playerStats.increaseWill(AbilityScores.getModifierValue(abilityScores.wisdom));
        playerStats.increaseMelee(AbilityScores.getModifierValue(abilityScores.strength));
        playerStats.increaseRanged(AbilityScores.getModifierValue(abilityScores.dexterity));
        playerStats.increaseCmb(AbilityScores.getModifierValue(abilityScores.dexterity));
        playerStats.increaseCmd(AbilityScores.getModifierValue(abilityScores.dexterity));
        playerStats.increaseCmd(AbilityScores.getModifierValue(abilityScores.strength));
    }

    public void save() {
        DBController.updateObject(this);
        DBController.updateObject(abilityScores);
        DBController.updateObject(playerSkills);
        DBController.updateObject(playerStats);
        pushHistory();
    }

    private void pushHistory(){
        while(!playerHistory.isEmpty()){
            try {
                DBController.insertObject(playerHistory.get(0));
                playerHistory.remove(0);
            } catch(SQLException e){
                System.err.println(String.format("Could not save all playerHistory: %s", e.getMessage()));
            }
        }

    }

    private void applyModifiersToSkills(){
        Field[] skills = playerSkills.getClass().getFields();
        for(Field skill: skills){
            if(skill.getName().equals("id")){
                continue;
            }
            try {
                ABILITY abilityEnum = SkillMap.getAbility(skill.getName());
                SKILL skillEnum = SKILL.valueOf(skill.getName());

                Field abilityStat = AbilityScores.class.getDeclaredField(abilityEnum.name());
                int abilityValue = abilityStat.getInt(abilityScores);
                int abilityModifier = AbilityScores.getModifierValue(abilityValue);
                playerSkills.increaseSkillByAbility(skillEnum, abilityEnum, abilityModifier);

                PlayerHistory skillChange = new PlayerHistory(id);
                skillChange.setCause_table(DBController.getTable(AbilityScores.class));
                skillChange.setCause_field(abilityEnum.toString());
                skillChange.setCause_value(String.valueOf(abilityValue));
                skillChange.setChange_table(DBController.getTable(PlayerSkills.class));
                skillChange.setChange_field(skillEnum.toString());
                skillChange.setChange_value(String.valueOf(abilityModifier));

                playerHistory.add(skillChange);
            } catch (Exception e){
                System.err.println(String.format("Could not apply stat modifier to skills: %s", e.getMessage()));
            }
        }
    }
}
