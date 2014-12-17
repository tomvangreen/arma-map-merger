package sqmmerge.model;

import sqmmerge.Integrator
import sqmmerge.model.Writer.Control



public class StringArray implements Node<StringArray>{
	public String name
	public List<String> strings = new ArrayList<String>()

	@Override
	public List<Node> getChildren() {
		return [];
	}

	@Override
	public void read(MissionReader reader) {
		def line = reader.getLine()
		name = line.replace("[]=", '')
		line = reader.nextLine()
		if(!"{".equals(line)){
			reader.err("Expected '{'")
		}
		line = reader.nextLine()
		while(!line.equals("};")){
			def len = line.size() - 1
			if(line.endsWith(",")){
				len--
			}
			strings.add(line.substring(1, len))
			line = reader.nextLine()
		}
		reader.nextLine()
	}

	@Override
	public void write(Writer writer) {
		writer << Control.Next << name << '[]='
		writer << Control.Next << '{'

		if(strings.any()){
			writer << Control.Right

			for(int index = 0; index < strings.size(); index++){
				writer << Control.Next
				writer << '"' + strings.get(index) + '"'
				if(index < strings.size() - 1){
					writer << ','
				}
			}
			writer << Control.Left
		}
		writer << Control.Next << '};'
	}

	@Override
	public void integrate(StringArray other, Integrator integrator) {
		other.strings.each{
			if(!strings.contains(it)){
				strings.add(it)
			}
		}
	}
}
