package sqmmerge.model

class Mission implements Node{
	String name
	public StringArray addOns
	public StringArray addOnsAuto
	public KeyValue randomSeed
	public Intel intel
	public Groups groups
	@Override
	public List<Node> getChildren() {
		def list = new ArrayList<Node>()
		list.add(addOns)
		list.add(addOnsAuto)
		list.add(randomSeed)
		list.add(intel)
		list.add(groups)
		return list;
	}
}
