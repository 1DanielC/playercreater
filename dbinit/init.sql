CREATE USER PATHFINDER IDENTIFIED BY "pathfinder";
GRANT CREATE SESSION TO PATHFINDER;
GRANT UNLIMITED TABLESPACE TO PATHFINDER;

CREATE TABLE PATHFINDER.PLAYER(
	id NUMBER(5) not null PRIMARY KEY 
,	name VARCHAR2(20)  
,	player_name VARCHAR2(20)  
,	race VARCHAR2(20)  
,	player_class VARCHAR2(20)   
,	player_size VARCHAR2(20)  
,	gender VARCHAR2(20)  
,	height NUMBER(10)  
,	weight NUMBER(10)  
,	hair VARCHAR2(20)  
,	eyes VARCHAR2(20)  
,	skin VARCHAR2(20)  
,	age VARCHAR2(20)  
,	alignment VARCHAR2(20)  
);

CREATE TABLE PATHFINDER.player_stats (
	id NUMBER(5) not null primary key
,	strength NUMBER(3)   
,	dexterity NUMBER(3)   
,	constitution NUMBER(3)   
,	intelligence NUMBER(3)   
,	wisdom NUMBER(3)   
,	charisma NUMBER(3)   
,	hitpoints NUMBER(3)   
,	dodge NUMBER(3)   
,	natural NUMBER(3)   
,	deflect NUMBER(3)   
);
	
CREATE TABLE PATHFINDER.player_perk (
	player_id	NUMBER(5)  
,	modifier	VARCHAR2(20)  
);

CREATE TABLE PATHFINDER.modifier(
	id	NUMBER(5) not null primary key
,	name	VARCHAR2(50)   
,	type	VARCHAR2(20)   
,	stat	VARCHAR2(20)   
,	value	NUMBER(5)   
)

CREATE TABLE PATHFINDER.player_skills(
	id	NUMBER(5) not null primary key
	,acrobatics	NUMBER(3)  
	,appraise	NUMBER(3)  
	,bluff	NUMBER(3)  
	,climb	NUMBER(3)  
	,craft	NUMBER(3)  
	,diplomacy	NUMBER(3)  
	,disable	NUMBER(3)  
	,disguise	NUMBER(3)  
	,escape_artist	NUMBER(3)  
	,fly	NUMBER(3)  
	,handle_animal	NUMBER(3)  
	,heal	NUMBER(3)  
	,intimidate	NUMBER(3)  
	,Knowledge_Arc	NUMBER(3)  
	,Knowledge_Arch	NUMBER(3)  
	,Knowledge_Dng	NUMBER(3)  
	,Knowledge_Geo	NUMBER(3)  
	,Knowledge_His	NUMBER(3)  
	,Knowledge_Loc	NUMBER(3)  
	,Knowledge_Nat	NUMBER(3)  
	,Knowledge_Nob	NUMBER(3)  
	,Knowledge_Rel	NUMBER(3)  
	,Knowledge_Pla	NUMBER(3)  
	,Linguistics	NUMBER(3)  
	,perception	NUMBER(3)  
	,perform	NUMBER(3)  
	,profession	NUMBER(3)  
	,ride	NUMBER(3)  
	,sense_motive	NUMBER(3)  
	,sleight_of_hand	NUMBER(3)  
	,spellcraft	NUMBER(3)  
	,stealth	NUMBER(3)  
	,survival	NUMBER(3)  
	,swim	NUMBER(3)  
	,use_magic_device	NUMBER(3)  
);