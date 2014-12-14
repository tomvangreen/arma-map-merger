package sqmmerge.model


class MissionSet implements Node{
	public String version
	public Mission mission
	public Mission intro
	public Mission outroWin
	public Mission outroLoose
	@Override
	public List<Node> getChildren() {
		def list = new ArrayList<Node>()
		list.add(mission)
		list.add(intro)
		list.add(outroWin)
		list.add(outroLoose)
		return list;
	}
}
