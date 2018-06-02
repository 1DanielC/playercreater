package main.java.objects;

import main.java.db.DBController;

import java.sql.SQLException;

/**
 * Created by Daniel on 6/2/2018.
 */
public class PlayerStats {
    public int id;
    public int strength;
    public int dexterity;
    public int constitution;
    public int intelligence;
    public int wisdom;
    public int charisma;

    public PlayerStats(int id){
        this(0, 0, 0, 0, 0, 0);
        this.id = id;
    }

    public PlayerStats(int str, int dex, int con, int intel, int wis, int cha){
        this.strength = str;
        this.dexterity = dex;
        this.constitution = con;
        this.intelligence = intel;
        this.wisdom = wis;
        this.charisma = cha;
    }

    public int getId(){return id;}

    public static PlayerStats generate(int playerId) {
        try {
            PlayerStats playerStats = new PlayerStats(playerId);
            DBController.insertObject(playerStats);
            return playerStats;
        } catch (SQLException e){
            System.err.println(String.format("Cannot generate record: %s", e.getMessage()));
            return null;
        }
    }

    public void update(){
        DBController.updateObject(this);
    }

    public static int getModifierValue(int val){
        if(val == 10){
            return 0;
        } else if( val > 10 && val <=13){
            return 1;
        } else {
            return (int)Math.ceil((double)(val - 11) / 2);
        }
    }

    public static void main (String[] args){
        for(int i = 0; i < 30; i++) {
            System.out.println(i + ": " + PlayerStats.getModifierValue(i));
        }
    }
}
