package sqmmerge.model

import sqmmerge.Integrator
import sqmmerge.model.Writer.Control




public class Waypoint implements Node<Waypoint> {

	public KeyValue position
	public KeyValue type
	public KeyValue combat
	public Effects effects
	public KeyValue showWP
	public KeyValue id

	@Override
	public List<Node> getChildren() {
		[]
	}

	@Override
	public void read(MissionReader reader) {
		def line = reader.getLine()
		while(!"};".equals(line)){
			if(line.startsWith("position[]=")){
				position = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("type=")){
				type = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("id=")){
				id = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("combat=")){
				combat = new KeyValue(line)
				reader.nextLine()
			}
			else if("class Effects".equals(line)){
				effects = new Effects()
				effects.read(reader)
			}
			else if(line.startsWith("showWP=")){
				showWP = new KeyValue(line)
				reader.nextLine()
			}
			else{
				reader.err('Waypoint: Unknown situation: ', true)
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
		id?.write(writer)
		type?.write(writer)
		combat?.write(writer)
		effects?.write(writer)
		showWP?.write(writer)

		writer << Control.Left << Control.Next
		writer << '};'
	}

	@Override
	public void integrate(Waypoint node, Integrator integrator) {
		// TODO Auto-generated method stub

	}
}
