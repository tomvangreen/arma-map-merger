package sqmmerge.model

class Groups extends ItemList<Group>{

	@Override
	public String getListName() {
		return "Groups";
	}

	@Override
	public Group instanciateChild() {
		return new Group();
	}
}
