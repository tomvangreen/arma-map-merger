if (! isServer) exitWith {};

_crate = _this select 0;


// while {alive _crate} do
// {


clearMagazineCargo _crate;
clearWeaponCargo _crate;
clearItemCargoGlobal _crate;

// Weapons
_crate addWeaponCargoGlobal ["srifle_LRR_SOS_F", 2];
_crate addWeaponCargoGlobal ["launch_B_Titan_F", 2];
_crate addWeaponCargoGlobal ["arifle_TRG21_ARCO_pointer_F", 2];
_crate addWeaponCargoGlobal ["LMG_Mk200_MRCO_F", 1];
_crate addWeaponCargoGlobal ["hgun_P07_snds_F", 10];

// Ammo
_crate addMagazineCargoGlobal ["Titan_AA", 3];
_crate addMagazineCargoGlobal ["7Rnd_408_Mag", 6];
_crate addMagazineCargoGlobal ["30Rnd_9x21_Mag", 30];
_crate addMagazineCargoGlobal ["30Rnd_556x45_Stanag", 8];
_crate addMagazineCargoGlobal ["200Rnd_65x39_cased_Box", 3];

// Explosives
// _crate addMagazineCargoGlobal ["ATMine_Range_Mag", 6];
// _crate addMagazineCargoGlobal ["APERSMine_Range_Mag", 3];
// _crate addMagazineCargoGlobal ["ClaymoreDirectionalMine_Remote_Mag", 2];
// _crate addMagazineCargoGlobal ["DemoCharge_Remote_Mag", 2];
// _crate addMagazineCargoGlobal ["APERSBoundingMine_Range_Mag", 3];
// _crate addMagazineCargoGlobal ["SLAMDirectionalMine_Wire_Mag", 3];
// _crate addMagazineCargoGlobal ["APERSTripMine_Wire_Mag", 2];
// _crate addWeaponCargoGlobal ["ToolKit", 1];
// _crate addWeaponCargoGlobal ["MineDetector", 1];
// _crate addWeaponCargoGlobal ["Medikit", 1];

// Grenades
_crate addMagazineCargoGlobal ["1Rnd_HE_Grenade_shell", 10];
_crate addMagazineCargoGlobal ["1Rnd_Smoke_Grenade_shell", 10];


// HUD
_crate addItemCargoGlobal ["Zasleh2",5]; 
_crate addItemCargoGlobal ["muzzle_snds_H", 5]; 
_crate addItemCargoGlobal ["muzzle_snds_L", 5]; 
_crate addItemCargoGlobal ["muzzle_snds_B", 5]; 
_crate addItemCargoGlobal ["muzzle_snds_H_MG", 5]; 
_crate addItemCargoGlobal ["optic_Arco", 5]; 
_crate addItemCargoGlobal ["optic_Hamr", 5]; 
_crate addItemCargoGlobal ["optic_Aco", 5]; 
_crate addItemCargoGlobal ["optic_ACO_grn",5]; 
_crate addItemCargoGlobal ["optic_Holosight", 5]; 
_crate addItemCargoGlobal ["acc_flashlight", 5]; 
_crate addItemCargoGlobal ["acc_pointer_IR", 5];

_crate addItemCargoGlobal ["FirstAidKit", 5];


// Cloth
_crate addItemCargoGlobal ["V_Rangemaster_belt", 1];
_crate addItemCargoGlobal ["V_BandollierB_cbr", 1];
_crate addItemCargoGlobal ["V_PlateCarrier1_rgr", 1];
_crate addItemCargoGlobal ["V_Chestrig_khk", 1];
_crate addItemCargoGlobal ["H_HelmetB", 3];
_crate addItemCargoGlobal ["H_Booniehat_khk", 1];