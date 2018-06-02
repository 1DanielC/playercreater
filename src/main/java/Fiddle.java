package main.java;

import main.java.objects.Player;

import java.sql.*;

/**
 * Created by Daniel on 5/28/2018.
 */
public class Fiddle {
    Connection con = null;

    public Fiddle(){
        Player player = Player.generate();
        player.setName("Gerald");
        player.setAge("17");
        player.setPlayerClass("Rogue");
        player.setAlignment("Chaotic Good");
        player.setEyes("Green");
        player.setGender("Unidentified");
        player.setHair("Black");
        player.setHeight(154);
        player.setWeight(112);
        player.setPlayer_name("Daniel");
        player.setPlayerSize("Small");
        player.setSkin("Tan");
        player.update();
    }

    public static void main(String[] args){
        new Fiddle();
    }
}
