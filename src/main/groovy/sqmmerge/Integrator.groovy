package sqmmerge;

import java.util.regex.Pattern

public class Integrator {
	public MissionData result;
	private final Loader loader = new Loader()
	private final Writer writer = new Writer()

	Pattern listPattern = ~/items=([0-9]*);/


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


	public void integrate(Configuration config ){
		loader.debug = false
		for(MissionConfiguration mission : config.missions){
			String content = new File(mission.file).text
			MissionData data = loader.load(content)
			if(result == null){
				result = data
			}
			else{
				integrate(data, result)
			}
		}

		String resultText = writer.write(result)
		//		println(resultText)
		new File(config.outputFile).write(resultText)
	}

	private Node sourceNode
	private Node targetNode

	public void integrate(MissionData source, MissionData target){
		integrate(source.root, target.root)
	}

	public boolean debug = true
	public void integrate(Node source, Node target){
		if(debug){
			println(indent + "Integrate Node: " + source + " into target " + target )
		}
		if(source == null){
			System.err.println(indent + "Source node is null")
			return
		}
		if(target == null){
			System.err.println(indent + "Target node is null")
			return
		}
		boolean sameType = source.type == target.type
		boolean handled = false
		if(source.type == NodeType.Root){
			if(!sameType){
				System.err.println(indent + "Root node must exist")
				return
			}
			appendListContent(source, target)
		}
		else if(source.type == NodeType.Class){
			integrateClass(source, target)
		}
		else if(source.type == NodeType.Array){
			if(!sameType){
				System.err.println(indent + "Array can only be integrated with Array")
				return
			}
			integrateArray(source, target)
		}
		else if(source.type == NodeType.List){
			if(!sameType){
				System.err.println(indent + "List can only be integrated with Array")
				return
			}
			integrateList(source, target)
		}
		else if(source.type == NodeType.ArrayItem){
			//No Error
		}
		else if(source.type == NodeType.SemicolonTerminatedLine){
			//No Error
		}
		else{
			System.err.println(indent + "Integration Unhandled: " + source.type + "->" + target.type)
		}
	}

	public void appendListContent(Node source, Node target){
		int sourceIndex = 0
		int targetIndex = 0

		int sourceSize = target.children.size()
		int targetSize = target.children.size()


		for(int index = 0; index < targetSize; index++){
			def targetChild = target.children.get(index)
			source.children.add(copyElement(targetChild, source))
		}
	}

	public Node copyElement(Node element, Node parent){
		NodeType type = element.type
		def copy = new Node(type, parent, element.data)

		integrate(element, copy)

		copy
	}

	public Node integrateClass(Node source, Node target){
		target.data = source.data
		for(Node child : source.children){
			target.children.add(copyElement(child, target))
		}
	}

	public Node integrateArray(Node source, Node target){
		target.data = source.data
		for(Node child : source.children){
			target.children.add(copyElement(child, target))
		}
	}

	public Node integrateList(Node source, Node target){
		target.data = source.data
		for(Node child : source.children){
			String data = source.data
			def matcher = listPattern.matcher(data == null ? "" : data)
			if(matcher.matches()){
				data = "Item" + (target.children.size() + 1)
			}
			Node copy = copyElement(child, target)
			copy.data = data
			target.children.add(copy)
		}
	}
}