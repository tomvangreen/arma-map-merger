package sqmmerge.model

import sqmmerge.Integrator
import sqmmerge.model.Writer.Control




public class Marker implements Node<Marker> {

	public KeyValue position
	public KeyValue name
	public KeyValue text
	public KeyValue type
	public KeyValue colorName
	public KeyValue markerType
	public KeyValue a
	public KeyValue b
	public KeyValue angle
	public KeyValue fillName
	public KeyValue drawBorder

	@Override
	public List<Node> getChildren() {
		return []
	}

	@Override
	public void read(MissionReader reader) {
		def line = reader.getLine()
		while(!"};".equals(line)){
			if(line.startsWith("position[]=")){
				position = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("name=")){
				name = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("text=")){
				text = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("type=")){
				type = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("markerType=")){
				markerType = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("a=")){
				a = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("b=")){
				b = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("angle=")){
				angle = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("colorName=")){
				colorName = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("fillName=")){
				fillName = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("drawBorder=")){
				drawBorder = new KeyValue(line)
				reader.nextLine()
			}
			else{
				reader.err('Marker:  Unknown situation: ', true)
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
		name?.write(writer)
		text?.write(writer)
		markerType?.write(writer)
		type?.write(writer)
		colorName?.write(writer)
		a?.write(writer)
		b?.write(writer)
		angle?.write(writer)
		fillName?.write(writer)
		drawBorder?.write(writer)

		writer << Control.Left << Control.Next
		writer << '};'
	}

	@Override
	public void integrate(Marker node, Integrator integrator) {
		// TODO Auto-generated method stub

	}
}
