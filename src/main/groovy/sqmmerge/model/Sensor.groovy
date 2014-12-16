package sqmmerge.model

import sqmmerge.model.Writer.Control





public class Sensor implements Node {

	public KeyValue position
	public KeyValue activationBy
	public KeyValue repeating
	public KeyValue interruptable
	public KeyValue age
	public KeyValue expActiv
	public Effects effects

	@Override
	public List<Node> getChildren() {
		[]
	}

	@Override
	public void read(Reader reader) {
		def line = reader.getLine()
		while(!"};".equals(line)){
			if(line.startsWith("position[]=")){
				position = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("activationBy=")){
				activationBy = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("repeating=")){
				repeating = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("interruptable=")){
				interruptable = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("age=")){
				age = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("expActiv=")){
				expActiv = new KeyValue(line)
				reader.nextLine()
			}
			else if("class Effects".equals(line)){
				effects = new Effects()
				effects.read(reader)
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

		position?.write(writer)
		activationBy?.write(writer)
		repeating?.write(writer)
		interruptable?.write(writer)
		age?.write(writer)
		expActiv?.write(writer)
		effects?.write(writer)


		writer << Control.Left << Control.Next
		writer << '};'
	}
}
