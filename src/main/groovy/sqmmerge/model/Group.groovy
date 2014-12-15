package sqmmerge.model

import sqmmerge.model.Writer.Control




public class Group implements Node {

	KeyValue side
	Vehicles vehicles

	@Override
	public List<Node> getChildren() {
		[]
	}

	@Override
	public void read(Reader reader) {
		def line = reader.getLine()
		while(!"};".equals(line)){
			if(line.startsWith("side=")){
				side = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("class Vehicles")){
				vehicles = new Vehicles()
				vehicles.read(reader)
			}
			else{
				reader.err('Unknown situation: ', false)
				reader.err(line)
			}
			line = reader.getLine()
		}
	}

	@Override
	public void write(Writer writer) {
		writer << Control.Next << '{'
		writer << Control.Right

		side?.write(writer)
		vehicles.write(writer)

		writer << Control.Left << Control.Next
		writer << '};'
	}
}
