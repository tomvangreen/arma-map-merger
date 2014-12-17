package sqmmerge.model

import sqmmerge.Integrator
import sqmmerge.model.Writer.Control




public class Group implements Node<Group> {

	KeyValue side
	Vehicles vehicles
	Waypoints waypoints

	@Override
	public List<Node> getChildren() {
		[]
	}

	@Override
	public void read(MissionReader reader) {
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
			else if("class Waypoints".equals(line)){
				waypoints = new Waypoints()
				waypoints.read(reader)
			}
			else{
				reader.err('Group: Unknown situation: ', true)
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
		vehicles?.write(writer)
		waypoints?.write(writer)

		writer << Control.Left << Control.Next
		writer << '};'
	}

	@Override
	public void integrate(Group node, Integrator integrator) {
		// TODO Auto-generated method stub

	}
}
