waitUntil {!isNull player};       //to prevent MP / JIP issues

_unit = _this select 0;

//This script handles the setup of a player after spawn

//waitUntil {!isNull player};       //to prevent MP / JIP issues


//taskKey = "bms_lighthouse_" + _unit;

//task = _unit createSimpleTask[taskKey];
//task setSimpleTaskDescription ["Take the Lighthouse"];
//task setSimpleTaskTarget getPos bmsLightSpawn


removeallweapons _unit;
removeallassigneditems _unit;
removeallcontainers _unit;

_unit addbackpack "B_Kitbag_Base"; 
_unit addvest "V_BandollierB_cbr"; 
_unit adduniform "U_B_CombatUniform_mcam_tshirt"; 
_unit addheadgear "H_HelmetB_light"; 
//_unit addmagazines ["100Rnd_65x39_caseless_mag_Tracer", 1];    //<- it is addmagazines ["string", number] or addmagazine "string"!
_unit addmagazines ["30Rnd_65x39_caseless_mag_Tracer", 9]; 
_unit addmagazines ["3Rnd_HE_Grenade_shell", 3]; 
_unit addweapon "arifle_MX_GL_F"; 
_unit addmagazines ["16Rnd_9x21_Mag", 6]; 
_unit addweapon "hgun_Rook40_F"; 
_unit addweapon "ItemGPS";
_unit addItemCargo ["FirstAidKit",2];//_unit addPrimaryWeaponItem "optic_Hamr"; 
_unit addPrimaryWeaponItem "optic_Arco"; 
//_unit addPrimaryWeaponItem "acc_flashlight"; 
_unit addweapon "NVGoggles";
/* you'd probably also use here: 
* _unit assignItem "NVGoggles";
*/

//if(true) exitWith{};