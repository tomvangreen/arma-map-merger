package sqmmerge.model

class Args extends ItemList<Arg>{

	@Override
	public String getListName() {
		return "args";
	}

	@Override
	public Arg instanciateChild() {
		return new Arg();
	}
}
