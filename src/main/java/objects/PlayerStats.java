package main.java.objects;

import main.java.db.PlayerStatsDB;

/**
 * Created by Daniel on 6/2/2018.
 */
public class PlayerStats extends PlayerStatsDB{

    public PlayerStats(int str, int dex, int con, int intel, int wis, int cha){
        super();
        this.strength = str;
        this.dexterity = dex;
        this.constitution = con;
        this.intelligense = intel;
        this.wisdom = wis;
        this.charisma = cha;
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
