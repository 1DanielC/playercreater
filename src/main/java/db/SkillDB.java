package main.java.db;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Daniel on 6/2/2018.
 */
public class SkillDB {
    private static String TABLE = "PLAYER_SKILLS";
    private static String SEQUENCE = "PLAYER_SKILLS_SEQ";

    public int id;
    String acrobatics;
    String appraise;
    String bluff;
    String climb;
    String craft;
    String diplomacy;
    String disable;
    String disguise;
    String escape_artist;
    String fly;
    String handle_animal;
    String heal;
    String intimidate;
    String knowledge_Arc;
    String knowledge_Arch;
    String knowledge_Dng;
    String knowledge_Geo;
    String knowledge_His;
    String knowledge_Loc;
    String knowledge_Nat;
    String knowledge_Nob;
    String knowledge_Rel;
    String knowledge_Pla;
    String linguistics;
    String perception;
    String perform;
    String profession;
    String ride;
    String sense_motive;
    String sleight_of_hand;
    String spellcraft;
    String stealth;
    String survival;
    String swim;
    String use_magic_device;


    public SkillDB () {
    }

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

    public void insertPlayer(Connection con) throws SQLException {
        if(!isValid()){
            System.err.println("Cannot add player; invalid id");
            return;
        }

        try {
            Field[] fields = this.getClass().getFields();
            List<String> variables = Arrays.asList(fields).stream()
                    .map(Field::getName)
                    .collect(Collectors.toList());
            List<String> values = Stream.of(fields)
                    .map(field -> {
                        try {
                            Object obj = field.get(this);
                            return obj == null ? "null" :
                                    obj instanceof Integer ? obj.toString() :
                                            String.format("%s%s%s", "'", obj.toString(), "'");
                        } catch (Exception e) {
                            System.err.println("Can't get all field values");
                        }
                        return "";
                    })
                    .collect(Collectors.toList());
            String insertStatement = String.format("INSERT INTO %s (%s) VALUES (%s)", "PLAYER", String.join(", ", variables), String.join(", ", values));
            PreparedStatement statement = con.prepareStatement(insertStatement);
            statement.execute();
        } catch(SQLException e){
            System.err.println(String.format("Problem inserting to %s", TABLE));
            throw e;
        }
    }

    public boolean isValid(){
        return id != -1;
    }

    public void updatePlayer(Connection con){

    }

}
