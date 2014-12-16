package sqmmerge.model




class Effects extends ItemList<Group>{

	@Override
	public String getListName() {
		return "Effects";
	}

	@Override
	public Group instanciateChild() {
		return new Effect();
	}
}
