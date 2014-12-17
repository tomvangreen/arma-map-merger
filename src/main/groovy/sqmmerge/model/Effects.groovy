package sqmmerge.model




class Effects extends ItemList<Effect>{

	@Override
	public String getListName() {
		return "Effects";
	}

	@Override
	public Effect instanciateChild() {
		return new Effect();
	}
}
