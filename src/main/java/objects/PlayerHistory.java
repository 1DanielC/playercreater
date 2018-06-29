package main.java.objects;

import main.java.db.DBController;

import java.lang.reflect.Field;
import java.sql.SQLException;

/**
 * Created by Daniel on 6/2/2018.
 */
public class PlayerHistory {
    public int id;

    public String description;
    public String cause_table;
    public String cause_field;
    public String cause_value;
    public String change_table;
    public String change_field;
    public String change_value;
    public String duration;

    public PlayerHistory(int id) {
        this.id = id;
    }

    public static PlayerHistory generate(int playerId) {
        try {
            if (playerId != -1) {
                PlayerHistory playerHistory = new PlayerHistory(playerId);
                DBController.insertObject(playerHistory);
                return playerHistory;
            } else {
                System.err.println("Cannot generate Player_History: id is -1");
                return null;
            }
        } catch (SQLException e) {
            System.err.println(String.format("Cannot generate record: %s", e.getMessage()));
            return null;
        }
    }

    public void update() {
        DBController.updateObject(this);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCause_table(String cause_table) {
        this.cause_table = cause_table;
    }

    public void setCause_field(String cause_field) {
        this.cause_field = cause_field;
    }

    public void setCause_value(String cause_value) {
        this.cause_value = cause_value;
    }

    public void setChange_table(String change_table) {
        this.change_table = change_table;
    }

    public void setChange_field(String change_field) {
        this.change_field = change_field;
    }

    public void setChange_value(String change_value) {
        this.change_value = change_value;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}