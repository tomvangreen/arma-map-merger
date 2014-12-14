import java.util.regex.Matcher
import java.util.regex.Pattern




class Loader {
	private String[] lines
	public boolean debug = true;
	String indent = ""



	Pattern classPattern = ~/class (\w*)/
	Pattern listPattern = ~/items=([0-9]*);/
	Pattern simpleArrayPattern = ~/([\w]*)\[\]=/


	def indentRight(){
		indent = indent + " "

		//println(indent + "> Indent on: " + indent)
	}

	def indentLeft() {
		if(indent.length() > 1){
			indent = indent.substring(1)
		}
		else if(indent.length() == 1){
			indent = ""
		}
		//println(indent + "< Indent on: " + indent)
	}

	MissionData load(String content){
		MissionData data = new MissionData()
		lines = content.split('\n')

		data.version = lines[0]

		int row = 1
		while(row < lines.length){
			row = loadClass(data.root, row)
			row ++
		}

		data
	}

	int loadClassContents(Node parent, int row){
		def line = lines[row].trim()
		def classMatcher = classPattern.matcher(line)
		if(classMatcher.matches()){
			return loadClass(parent, row)
		}
		def listMatcher = listPattern.matcher(line)
		if(listMatcher.matches()){
			return loadList(parent, row, listMatcher)
		}

		def arrayMatcher = simpleArrayPattern.matcher(line)
		if(arrayMatcher.matches()){
			return loadArray(parent, row, arrayMatcher)
		}

		if(line.endsWith("};")){
			System.err.println(indent + "Line " + row +" : }; should not appear at this position.")
			return row
		}

		if(line.endsWith(";")){
			indentRight()
			if(debug){
				println(indent + "SemicolonTerminatedLine: " + line)
			}
			def node = new Node(NodeType.SemicolonTerminatedLine, parent)
			node.data = line
			parent.insertChild(node)
			indentLeft()
			return row
		}
		System.err.println("Line " + row +" : This part should not be called at all. Something has not been read correctly on line " + (row + 1))
		System.err.println(line)
		row
	}

	int loadArray(Node parent, int row, Matcher listMatcher){
		indentRight()

		def name = listMatcher.group(0)
		if(debug){
			println(indent + "Loading array " + name)
		}
		row++


		if(!lines[row].trim().equals("{")){
			println(indent + "Found unexpected content: ")
			println(indent + lines[row].trim())
		}

		row++;
		def array = new Node(NodeType.Array, parent, name)
		parent.insertChild(name, array)

		def line = lines[row].trim()
		indentRight()
		while(!line.equals("};")) {

			if(line.endsWith(",")){
				line = line.substring(0, line.length() - 1)
			}
			def node = new Node(NodeType.ArrayItem, parent, line)
			if(debug){
				println(indent + "ArrayItem: " + line)
			}
			array.insertChild(node)

			row++
			line = lines[row].trim()
		}
		indentLeft()
		row++
		indentLeft()
		return row
	}
	int loadList(Node parent, int row, Matcher listMatcher){
		indentRight()

		if(debug){
			println(indent + "Loading list")
		}

		row++

		def list = new Node(NodeType.List, parent)

		parent.insertChild(list)

		def line = lines[row].trim()
		while(row < lines.size() && !line.equals("};")) {
			if(debug){
				println(indent + "List Item")
			}
			def matcher= classPattern.matcher(line)
			if(matcher.matches()){
				row = loadClass(list, row)
			}
			row ++
		}

		indentLeft()
		return row
	}

	int loadClass(Node parent, int row){
		indentRight()
		def line = lines[row].trim()
		def classMatcher = classPattern.matcher(line)
		if(!classMatcher.matches()){
			System.err.println(indent + "Line " + row + ": Cannot write class: " +line )
			return row
		}
		def name = classMatcher.group(0)
		if(debug){
			println(indent + "Loading class " + name)
		}
		def clazz = new Node(NodeType.Class, parent, name)
		parent.insertChild(name, clazz)

		row++
		if(row >= lines.size()){
			System.err.println(indent + "Line " + row +" : Row index is out of bounds: " + row);
			return row;
		}
		if(!lines[row].trim().equals("{")){
			println("Found unexpected content: ")
			println(lines[row].trim())
		}
		row++
		int oldRow = 0
		while(row < lines.length && !lines[row].trim().equals("};")){
			oldRow =  row;
			row = loadClassContents(clazz, row)
			if(oldRow == row){
				row++
			}
		}
		indentLeft()
		row + 1
	}
}
