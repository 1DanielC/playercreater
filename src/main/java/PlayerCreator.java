package main.java;

import main.java.objects.Player;
import main.java.objects.enums.*;

import java.sql.*;

/**
 * Created by Daniel on 5/28/2018.
 */
public class PlayerCreator {
    Connection con = null;

    public void makeCharacter(){
        Player player = Player.generate();
        player.setName("Churgy");
        player.setPlayerName("Daniel");
        player.setRace(RACE.goblin);
        player.setPlayerSize(SIZE.small);
        player.setAge(17);
        player.setPlayerClass(PLAYERCLASS.rogue);
        player.setAlignment(ALIGNMENT.chaotic_good);
        player.setEyes("Green");
        player.setGender(GENDER.NONCIS);
        player.setHair("Black");
        player.setHeight(154);
        player.setWeight(112);
        player.setSkin("Tan");

        player.setAbilityScores(13, 16, 12, 9, 11, 11);
        player.setStats();

        player.save();
    }

    public PlayerCreator(){

    }

    public static void main(String[] args){
        new PlayerCreator().makeCharacter();
    }
}
