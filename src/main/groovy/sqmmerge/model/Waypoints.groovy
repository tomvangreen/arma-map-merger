package sqmmerge.model;

public class Waypoints extends ItemList<Waypoint> {

	@Override
	public String getListName() {
		return "Waypoints";
	}

	@Override
	public Waypoint instanciateChild() {
		return new Waypoint();
	}
}
