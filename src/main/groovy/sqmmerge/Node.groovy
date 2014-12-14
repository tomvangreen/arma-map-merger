package sqmmerge

public class Node {
	public final List<Node> children = new ArrayList<Node>()
	public final Map<String, Node> childrenMap = new HashMap<String, Node>()

	public String data
	public final NodeType type
	public final Node parent


	public Node(NodeType type, Node parent) {
		this(type, parent, null)
	}
	public Node(NodeType type, Node parent, String data) {
		this.type = type
		this.parent = parent
		this.data = data
	}


	public void insertChild(Node child){
		insertChild(null, child)
	}
	public void insertChild(String key, Node child){
		children.add(child)
		if(key){
			childrenMap.putAt(key, child)
		}
	}
}
