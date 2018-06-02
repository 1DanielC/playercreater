package main.java.objects;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Daniel on 6/2/2018.
 */
public class Skill {
        private SKILL skill;
        private ABILITY ability;
        private int modifier;

        private enum SKILL {
            acrobatics
            ,appraise
            ,bluff
            ,climb
            ,craft
            ,diplomacy
            ,disable
            ,disguise
            ,escape_artist
            ,fly
            ,handle_animal
            ,heal
            ,intimidate
            ,knowledge_Arc
            ,knowledge_Arch
            ,knowledge_Dng
            ,knowledge_Geo
            ,knowledge_His
            ,knowledge_Loc
            ,knowledge_Nat
            ,knowledge_Nob
            ,knowledge_Rel
            ,knowledge_Pla
            ,linguistics
            ,perception
            ,perform
            ,profession
            ,ride
            ,sense_motive
            ,sleight_of_hand
            ,spellcraft
            ,stealth
            ,survival
            ,swim
            ,use_magic_device
        }

        private enum ABILITY {
            strength
            ,dexterity
            ,constitution
            ,intelligence
            ,wisdom
            ,charisma
        }

        public List<Pair<SKILL, ABILITY>> mappings = new ArrayList<>();

        private void construct(){
        mappings.add(new Pair(SKILL.acrobatics, ABILITY.strength));
        mappings.add(new Pair(SKILL.appraise, ABILITY.intelligence));
        mappings.add(new Pair(SKILL.bluff, ABILITY.charisma));
        mappings.add(new Pair(SKILL.climb, ABILITY.strength));
        mappings.add(new Pair(SKILL.craft, ABILITY.intelligence));
        mappings.add(new Pair(SKILL.diplomacy, ABILITY.charisma));
        mappings.add(new Pair(SKILL.disable, ABILITY.dexterity));
        mappings.add(new Pair(SKILL.disguise, ABILITY.charisma));
        mappings.add(new Pair(SKILL.escape_artist, ABILITY.dexterity));
        mappings.add(new Pair(SKILL.fly, ABILITY.dexterity));
        mappings.add(new Pair(SKILL.handle_animal, ABILITY.charisma));
        mappings.add(new Pair(SKILL.heal, ABILITY.wisdom));
        mappings.add(new Pair(SKILL.intimidate, ABILITY.charisma));
        mappings.add(new Pair(SKILL.knowledge_Arc, ABILITY.intelligence));
        mappings.add(new Pair(SKILL.knowledge_Arch, ABILITY.intelligence));
        mappings.add(new Pair(SKILL.knowledge_Dng, ABILITY.intelligence));
        mappings.add(new Pair(SKILL.knowledge_Geo, ABILITY.intelligence));
        mappings.add(new Pair(SKILL.knowledge_His, ABILITY.intelligence));
        mappings.add(new Pair(SKILL.knowledge_Loc, ABILITY.intelligence));
        mappings.add(new Pair(SKILL.knowledge_Nat, ABILITY.intelligence));
        mappings.add(new Pair(SKILL.knowledge_Nob, ABILITY.intelligence));
        mappings.add(new Pair(SKILL.knowledge_Rel, ABILITY.intelligence));
        mappings.add(new Pair(SKILL.knowledge_Pla, ABILITY.intelligence));
        mappings.add(new Pair(SKILL.linguistics, ABILITY.intelligence));
        mappings.add(new Pair(SKILL.perception, ABILITY.wisdom));
        mappings.add(new Pair(SKILL.perform, ABILITY.charisma));
        mappings.add(new Pair(SKILL.profession, ABILITY.wisdom));
        mappings.add(new Pair(SKILL.ride, ABILITY.dexterity));
        mappings.add(new Pair(SKILL.sense_motive, ABILITY.wisdom));
        mappings.add(new Pair(SKILL.sleight_of_hand, ABILITY.dexterity));
        mappings.add(new Pair(SKILL.spellcraft, ABILITY.intelligence));
        mappings.add(new Pair(SKILL.stealth, ABILITY.dexterity));
        mappings.add(new Pair(SKILL.survival, ABILITY.wisdom));
        mappings.add(new Pair(SKILL.swim, ABILITY.strength));
        mappings.add(new Pair(SKILL.use_magic_device, ABILITY.wisdom));
    }

        public static List<Skill> getSkills(){
            return Stream.of(SKILL.values())
                    .map(SKILL::name)
                    .map(name -> new Skill(name))
                    .collect(Collectors.toList());
        }

        public Skill(String name){
            construct();
            skill = SKILL.valueOf(name);
            mappings.get(0).getValue();
            ability = mappings.stream()
                    .filter(mapping -> mapping.getKey() == skill)
                    .findFirst()
                    .get().getValue();
        }

        public String getAbility() { return ability.toString(); }


        public static void main(String[] args){
            System.out.println(new Skill("acrobatics").getAbility());
        }
}
