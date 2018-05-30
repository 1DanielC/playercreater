package main.java;

import java.sql.*;
import java.util.Properties;

/**
 * Created by Daniel on 5/28/2018.
 */
public class Fiddle {
    Connection con = null;

    public Fiddle(){
        con = getConnection();
        Player player = new Player(generateId());
        player.insertPlayer(con);
        closeConnection();
    }

    public int generateId() {
        try {
            PreparedStatement statement = con.prepareStatement("SELECT player_seq.nextval id from dual");
            statement.execute();
            ResultSet results = statement.getResultSet();
            results.next();
            return results.getInt("id");
        } catch(SQLException e){
            System.err.println("Error getting new ID from player");
        }
        return -1;
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
