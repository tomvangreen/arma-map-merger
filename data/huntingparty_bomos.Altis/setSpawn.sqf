private ["_position", "_message"];

_position = _this select 0;
_message = _this select 1;

"respawn_west" setMarkerPos _position;

hintSilent parseText format["<t size='1' color='#ffffff'>" + _message + "</t>", name player];
