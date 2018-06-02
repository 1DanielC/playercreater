package main.java.objects;

import main.java.db.PlayerDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Daniel on 6/2/2018.
 */
public class Player extends PlayerDB{
    private List<Skill> skills;

    public Player(int id){
        this.id = id;

        skills = Skill.getSkills();
    }

    public static Player generatePlayer(Connection con) {
        try {
            int id = generateId(con);
            Player player = new Player(id);
            player.generateStats();
            player.insertPlayer(con);
            return player;
        } catch (SQLException e){
            return null;
        }
    }

    private void generateStats(){

    }

}
