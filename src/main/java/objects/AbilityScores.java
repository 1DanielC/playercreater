package main.java.objects;

import main.java.db.DBController;

import java.sql.SQLException;

/**
 * Created by Daniel on 6/2/2018.
 */
public class AbilityScores {
    public int id;
    public int strength;
    public int dexterity;
    public int constitution;
    public int intelligence;
    public int wisdom;
    public int charisma;

    public AbilityScores(){
        this(0, 0, 0, 0, 0, 0);
    }

    public AbilityScores(int id){
        this(0, 0, 0, 0, 0, 0);
        this.id = id;
    }

    public AbilityScores(int str, int dex, int con, int intel, int wis, int cha){
        this.strength = str;
        this.dexterity = dex;
        this.constitution = con;
        this.intelligence = intel;
        this.wisdom = wis;
        this.charisma = cha;
    }

    public int getId(){return id;}

    public static AbilityScores generate(int playerId) {
        try {
            AbilityScores abilityScores = new AbilityScores(playerId);
            DBController.insertObject(abilityScores);
            return abilityScores;
        } catch (SQLException e){
            System.err.println(String.format("Cannot generate record: %s", e.getMessage()));
            return null;
        }
    }

    public void update(){
        DBController.updateObject(this);
    }

    public static int getModifierValue(int val){
        if (val >= 10){
            return (int)Math.ceil((val - 10) / 2);
        } else {
            return (int)Math.ceil((val - 11) / 2);
        }
    }

    public static void main (String[] args){
        for(int i = -10; i < 30; i++) {
            System.out.println(i + ": " + AbilityScores.getModifierValue(i));
        }
    }
}
