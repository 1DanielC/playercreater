package main.java.db;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Daniel on 6/2/2018.
 */
public class PlayerStatsDB {
    private static String TABLE = "PLAYER";
    private static String SEQUENCE = "PLAYER_SEQ";

    public int id;
    public int strength;
    public int dexterity;
    public int constitution;
    public int intelligense;
    public int wisdom;
    public int charisma;


    public PlayerStatsDB(){}

    protected static int generateId(Connection con) {
        try {
            PreparedStatement statement = con.prepareStatement(String.format("SELECT %s.nextval id from dual", SEQUENCE));
            statement.execute();
            ResultSet results = statement.getResultSet();
            results.next();
            return results.getInt("id");
        } catch(SQLException e){
            System.err.println("Error getting new ID from player");
        }
        return -1;
    }

    public boolean isValid(){
        return id != -1;
    }

    protected void insertPlayer(Connection con) throws SQLException {
        if(!isValid()){
            System.err.println(String.format("Cannot add to %s; invalid id", TABLE));
            return;
        }
        List<String> fields = getFields();
        List<String> values = getValues();
        String insertStatement = String.format("INSERT INTO %s (%s) VALUES (%s)", "PLAYER", String.join(", ", fields), String.join(", ", values));
        runQuery(con, insertStatement);
    }

    public List<String> getFields(){
        return Stream.of(this.getClass().getFields())
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    public List<String> getValues(){
        return Stream.of(this.getClass().getFields())
                .map(field -> {
                    try {
                        return getInsertString(field.get(this));
                    } catch (Exception e) {
                        System.err.println("Can't get all field values");
                    }
                    return "";
                })
                .collect(Collectors.toList());
    }

    public String getInsertString(Object obj){
        return obj == null ? "null" :
                obj instanceof Integer ? obj.toString() :
                        String.format("%s%s%s", "'", obj.toString(), "'");
    }

    public void runQuery(Connection con, String query) throws SQLException{
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.execute();
        } catch(SQLException e) {
            System.err.println(String.format("Problem inserting to %s", TABLE));
            throw e;
        }
    }

    public void updatePlayer(Connection con) {
        if(!isValid()){
            System.err.println(String.format("Cannot update in %s; invalid id.", TABLE));
            return;
        }
        List<String> updates = Stream.of(this.getClass().getFields())
                .map(field -> {
                    try {
                        return String.format("%s = %s", field.getName(), getInsertString(field.get(this)));
                    } catch (Exception e) {
                        System.err.println(String.format("Problem building update statement for %s", TABLE));
                        return "";
                    }
                })
                .collect(Collectors.toList());
        String updateStatement = String.format("UPDATE %s SET %s WHERE ID = %s", TABLE, String.join(", ", updates), id);
        try{
            runQuery(con, updateStatement);
        } catch(SQLException e){
            return;
        }
    }
}
