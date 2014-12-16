package sqmmerge.model

class Sensors extends ItemList<Sensor>{

	@Override
	public String getListName() {
		return "Sensors";
	}

	@Override
	public Sensor instanciateChild() {
		return new Sensor();
	}
}
