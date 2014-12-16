package sqmmerge.model



class Markers extends ItemList<Marker>{

	@Override
	public String getListName() {
		return "Markers";
	}

	@Override
	public Marker instanciateChild() {
		return new Marker();
	}
}
