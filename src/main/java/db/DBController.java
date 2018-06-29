package main.java.db;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Daniel on 6/2/2018.
 */
public class DBController {
    private static String SEQUENCE_SUFFIX = "_SEQ";

    private static Connection getConnection(){
        Connection con = null;
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

    public static int generateId(Class _class) {
        Connection con = getConnection();
        String table = getTable(_class);
        String sequence = String.format("%s%s", table, SEQUENCE_SUFFIX);
        String sequenceQuery = String.format("SELECT %s.nextval id from dual", sequence);
        try {
            ResultSet results = runQuery(con,sequenceQuery);
            results.next();
            int id = results.getInt("id");
            con.close();
            return id;
        } catch(SQLException e){
            System.err.println(String.format("Error getting new ID from %s: %s", sequence, e.getMessage()));
        }
        return -1;
    }

    public static String getTable(Class _class){
        String name = _class.getSimpleName();
        String table = name.replaceAll("([A-Z])", "_$1").toUpperCase();
        return table.substring(1);
    }

    private static int getId(Object obj) {
        try {
            Method getIdMethod = obj.getClass().getDeclaredMethod("getId");
            return (int) getIdMethod.invoke(obj);
        } catch(Exception e){
            System.err.println(String.format("Error getting id from object: %s", e.getMessage()));
            return -1;
        }
    }

    public static void insertObject(Object obj) throws SQLException {
        String table = getTable(obj.getClass());
        List<String> fields = getFields(obj);
        List<String> values = getValues(obj);
        String insertStatement = String.format("INSERT INTO %s (%s) VALUES (%s)", table, String.join(", ", fields), String.join(", ", values));
        runQuery(insertStatement);
    }

    public static List<String> getFields(Object obj){
        return Stream.of(obj.getClass().getFields())
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    public static List<String> getValues(Object obj){
        return Stream.of(obj.getClass().getFields())
                .map(field -> {
                    try {
                        return getInsertString(field.get(obj));
                    } catch (Exception e) {
                        System.err.println("Can't get all field values");
                    }
                    return "";
                })
                .collect(Collectors.toList());
    }

    public static String getInsertString(Object obj){
        return obj == null ? "null" :
                obj instanceof Integer ? obj.toString() :
                        String.format("%s%s%s", "'", obj.toString(), "'");
    }

    private static ResultSet runQuery(String query) throws SQLException{
        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        con.close();
        return resultSet;
    }

    private static ResultSet runQuery(Connection con, String query) throws SQLException{
        PreparedStatement statement = con.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }

    public static void updateObject(Object obj) {
        String table = getTable(obj.getClass());
        List<String> updates = Stream.of(obj.getClass().getFields())
                .map(field -> {
                    try {
                        return String.format("%s = %s", field.getName(), getInsertString(field.get(obj)));
                    } catch (Exception e) {
                        System.err.println(String.format("Problem building update statement for %s", table));
                        return "";
                    }
                })
                .collect(Collectors.toList());
        try{
            int id = getId(obj);
            if(id != -1) {
                String updateStatement = String.format("UPDATE %s SET %s WHERE ID = %s", table, String.join(", ", updates), id);
                runQuery(updateStatement);
            }
        } catch(SQLException e){
            System.err.println(String.format("Error updating %s: %s", table, e.getMessage()));
            return;
        }
    }

}
