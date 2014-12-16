package sqmmerge.model


//TODO: Vehicles and Group should both have a base class handling stuff ;)

class Vehicles extends ItemList<Vehicle>{

	@Override
	public String getListName() {
		return "Vehicles";
	}

	@Override
	public Vehicle instanciateChild() {
		return new Vehicle();
	}

}
