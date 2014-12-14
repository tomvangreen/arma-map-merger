
public class Writer {
	private final StringBuilder builder = new StringBuilder()

	public final static NL = "\n"

	public final static String INDENT_BASE = "\t"
	public String indent = ""

	def indentRight(){
		indent = indent + INDENT_BASE

		//println(indent + "> Indent on: " + indent)
	}

	def indentLeft() {
		if(indent.length() > 1){
			indent = indent.substring(INDENT_BASE.length())
		}
		else if(indent.length() == 1){
			indent = ""
		}
		//println(indent + "< Indent on: " + indent)
	}



	public String write(MissionData data){
		builder << data.version << NL

		data.root.children.each{ writeNode(it) }

		String output = builder.toString()
		builder.setLength(0)
		output
	}

	public void writeNode(Node node){
		switch(node?.type){
			case NodeType.Class:
				writeClass(node)
				break;
			case NodeType.SemicolonTerminatedLine:
				writeSemicolonTerminatedLine(node)
				break;
			case NodeType.Array:
				writeArray(node)
				break;
			case NodeType.List:
				writeList(node)
				break;
			default:
				if(node){
					System.err.println("Node Type not supported yet: " + node.type)
				}
				else{
					System.err.println("Node empty!")
				}
				break;
		}
	}

	public void writeClass(Node node){
		builder << indent << node.data << NL
		builder << indent << "{" << NL
		indentRight()
		node.children.each{ writeNode(it) }

		indentLeft()
		builder << indent << "};" << NL
	}

	public void writeArray(Node node){
		builder << indent << node.data << NL
		builder << indent << "{" << NL
		indentRight()
		def last = node.children.last()
		node.children.each{ writeArrayNode(it, it != last) }
		indentLeft()
		builder << indent << "};" << NL
	}

	public void writeList(Node node){
		builder << indent << "items=" << node.children.size() << ";" << NL
		def last = node.children.last()
		node.children.each{ writeNode(writeClass(it)) }
	}

	public void writeArrayNode(Node node, boolean addComma){
		if(node.type != NodeType.ArrayItem){
			System.err.println("Expceted node type ArrayItem but found " + node.type)
			return
		}
		builder << indent << node.data
		if(addComma){
			builder << ","
		}
		builder << NL
	}

	public void writeSemicolonTerminatedLine(Node node){
		builder << indent << node.data << NL
	}
}
