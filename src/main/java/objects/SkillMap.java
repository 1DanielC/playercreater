package main.java.objects;

import main.java.objects.enums.ABILITY;
import main.java.objects.enums.SKILL;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniel on 6/2/2018.
 */
public class SkillMap {
    public static Map<SKILL, ABILITY> mappings = new HashMap<>();
    static {
        mappings.put(SKILL.acrobatics, ABILITY.strength);
        mappings.put(SKILL.appraise, ABILITY.intelligence);
        mappings.put(SKILL.bluff, ABILITY.charisma);
        mappings.put(SKILL.climb, ABILITY.strength);
        mappings.put(SKILL.craft, ABILITY.intelligence);
        mappings.put(SKILL.diplomacy, ABILITY.charisma);
        mappings.put(SKILL.disable, ABILITY.dexterity);
        mappings.put(SKILL.disguise, ABILITY.charisma);
        mappings.put(SKILL.escape_artist, ABILITY.dexterity);
        mappings.put(SKILL.fly, ABILITY.dexterity);
        mappings.put(SKILL.handle_animal, ABILITY.charisma);
        mappings.put(SKILL.heal, ABILITY.wisdom);
        mappings.put(SKILL.intimidate, ABILITY.charisma);
        mappings.put(SKILL.knowledge_arc, ABILITY.intelligence);
        mappings.put(SKILL.knowledge_arch, ABILITY.intelligence);
        mappings.put(SKILL.knowledge_dng, ABILITY.intelligence);
        mappings.put(SKILL.knowledge_geo, ABILITY.intelligence);
        mappings.put(SKILL.knowledge_his, ABILITY.intelligence);
        mappings.put(SKILL.knowledge_loc, ABILITY.intelligence);
        mappings.put(SKILL.knowledge_nat, ABILITY.intelligence);
        mappings.put(SKILL.knowledge_nob, ABILITY.intelligence);
        mappings.put(SKILL.knowledge_rel, ABILITY.intelligence);
        mappings.put(SKILL.knowledge_pla, ABILITY.intelligence);
        mappings.put(SKILL.linguistics, ABILITY.intelligence);
        mappings.put(SKILL.perception, ABILITY.wisdom);
        mappings.put(SKILL.perform, ABILITY.charisma);
        mappings.put(SKILL.profession, ABILITY.wisdom);
        mappings.put(SKILL.ride, ABILITY.dexterity);
        mappings.put(SKILL.sense_motive, ABILITY.wisdom);
        mappings.put(SKILL.sleight_of_hand, ABILITY.dexterity);
        mappings.put(SKILL.spellcraft, ABILITY.intelligence);
        mappings.put(SKILL.stealth, ABILITY.dexterity);
        mappings.put(SKILL.survival, ABILITY.wisdom);
        mappings.put(SKILL.swim, ABILITY.strength);
        mappings.put(SKILL.use_magic_device, ABILITY.wisdom);
    }

    public static ABILITY getAbility(String name) { return mappings.get(SKILL.valueOf(name)); }
    public static ABILITY getAbility(SKILL skill) { return mappings.get(skill); }

    public static void main(String[] args){
        System.out.println(SkillMap.getAbility(SKILL.perform));
    }
}
