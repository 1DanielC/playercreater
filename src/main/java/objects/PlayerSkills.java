package main.java.objects;

import main.java.db.DBController;
import main.java.objects.enums.ABILITY;
import main.java.objects.enums.DURATION;
import main.java.objects.enums.SKILL;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Daniel on 6/2/2018.
 */
public class PlayerSkills {
    public int id;

    public int acrobatics;
    public int appraise;
    public int bluff;
    public int climb;
    public int craft;
    public int diplomacy;
    public int disable;
    public int disguise;
    public int escape_artist;
    public int fly;
    public int handle_animal;
    public int heal;
    public int intimidate;
    public int knowledge_arc;
    public int knowledge_arch;
    public int knowledge_dng;
    public int knowledge_geo;
    public int knowledge_his;
    public int knowledge_loc;
    public int knowledge_nat;
    public int knowledge_nob;
    public int knowledge_rel;
    public int knowledge_pla;
    public int linguistics;
    public int perception;
    public int perform;
    public int profession;
    public int ride;
    public int sense_motive;
    public int sleight_of_hand;
    public int spellcraft;
    public int stealth;
    public int survival;
    public int swim;
    public int use_magic_device;

    public PlayerSkills(int id){
        this();
        this.id = id;
    }

    public PlayerSkills(){
        int acrobatics = 0;
        int appraise = 0;
        int bluff = 0;
        int climb = 0;
        int craft = 0;
        int diplomacy = 0;
        int disable = 0;
        int disguise = 0;
        int escape_artist = 0;
        int fly = 0;
        int handle_animal = 0;
        int heal = 0;
        int intimidate = 0;
        int knowledge_Arc = 0;
        int knowledge_Arch = 0;
        int knowledge_Dng = 0;
        int knowledge_Geo = 0;
        int knowledge_His = 0;
        int knowledge_Loc = 0;
        int knowledge_Nat = 0;
        int knowledge_Nob = 0;
        int knowledge_Rel = 0;
        int knowledge_Pla = 0;
        int linguistics = 0;
        int perception = 0;
        int perform = 0;
        int profession = 0;
        int ride = 0;
        int sense_motive = 0;
        int sleight_of_hand = 0;
        int spellcraft = 0;
        int stealth = 0;
        int survival = 0;
        int swim = 0;
        int use_magic_device = 0;
    }

    public int getId(){
        return id;
    }

    public static PlayerSkills generate(int playerId) {
        try {
            PlayerSkills playerSkills = new PlayerSkills(playerId);
            DBController.insertObject(playerSkills);
            return playerSkills;
        } catch (SQLException e){
            System.err.println(String.format("Cannot generate record: %s", e.getMessage()));
            return null;
        }
    }

    public void update(){
        DBController.updateObject(this);
    }

    public void setSkill(String name, int modifier){
        try {
            Field field = this.getClass().getField(name);
            field.setInt(this, modifier);
        } catch(Exception e){
            System.err.println(String.format("error setting skill: %s", e.getMessage()));
        }
    }

    public void increaseSkillByAbility(SKILL skill, ABILITY ability, int abilityValue){
        try{
            Field player_skill = PlayerSkills.class.getDeclaredField(skill.name());
            int currentValue = (int)player_skill.get(this);
            player_skill.setInt(this, currentValue + abilityValue);
        } catch(Exception e){
            System.err.println(String.format("could not increase Skill: %s", e.getMessage()));
        }
    }
}
