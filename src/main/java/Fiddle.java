package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Daniel on 5/28/2018.
 */
public class Fiddle {
    Connection con = null;

    public Fiddle(){
        con = getConnection();
        Player plat = new Player();
        plat.insertPlayer(con);
        closeConnection();
    }

    public Connection getConnection() {
        Properties connectionProps = new Properties();
        connectionProps.put("user", "daniel");
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
