package main.java;

import main.java.db.PlayerDB;
import main.java.objects.Player;

import java.sql.*;
import java.util.Properties;

/**
 * Created by Daniel on 5/28/2018.
 */
public class Fiddle {
    Connection con = null;

    public Fiddle(){
        con = getConnection();
        Player player = Player.generatePlayer(con);
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
        player.updatePlayer(con);
        closeConnection();
    }


    public Connection getConnection() {
        Properties connectionProps = new Properties();
        connectionProps.put("user", "pathfinder");
        connectionProps.put("password", "pathfinder");
        try {
          con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", connectionProps);
        } catch (SQLException e){
            System.err.println("Error Connecting to Database");
            System.exit(-1);
        }
        return con;
    }

    public void closeConnection(){
        try {
            con.close();
        } catch (SQLException e){
            System.err.println("Error Closing Database");
        }
    }
    public static void main(String[] args){
        new Fiddle();
    }
}
