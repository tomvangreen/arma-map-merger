package sqmmerge.model

import sqmmerge.Integrator
import sqmmerge.model.Writer.Control

public class Sensor implements Node<Sensor> {

	public KeyValue position
	public KeyValue activationBy
	public KeyValue repeating
	public KeyValue interruptable
	public KeyValue age
	public KeyValue angle
	public Effects effects
	public KeyValue timeout
	public KeyValue timeoutMin
	public KeyValue timeoutMid
	public KeyValue timeoutMax
	public KeyValue activationType
	public KeyValue a
	public KeyValue b
	public KeyValue type
	public KeyValue text
	public KeyValue name
	public KeyValue expCond
	public KeyValue expActiv
	public KeyValue expDesactiv

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
			else if(line.startsWith("type=")){
				type = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("text=")){
				text = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("name=")){
				name = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("expCond=")){
				expCond = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("expDesactiv=")){
				expDesactiv = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("angle=")){
				angle = new KeyValue(line)
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
			else if(line.startsWith("activationType=")){
				activationType = new KeyValue(line)
				reader.nextLine()
			}
			//			else if(line.startsWith("timeout=")){
			//				timeout = new KeyValue(line)
			//				reader.nextLine()
			//			}
			else if(line.startsWith("timeoutMin=")){
				timeoutMin = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("timeoutMid=")){
				timeoutMid = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("timeoutMax=")){
				timeoutMax = new KeyValue(line)
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
			else{
				reader.err('Sensor: Unknown situation: ', true)
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
		a?.write(writer)
		b?.write(writer)
		angle?.write(writer)
		activationBy?.write(writer)
		activationType?.write(writer)
		repeating?.write(writer)
		timeoutMin?.write(writer)
		timeoutMid?.write(writer)
		timeoutMax?.write(writer)
		interruptable?.write(writer)
		type?.write(writer)
		age?.write(writer)
		text?.write(writer)
		name?.write(writer)
		expCond?.write(writer)
		expActiv?.write(writer)
		expDesactiv?.write(writer)
		effects?.write(writer)


		writer << Control.Left << Control.Next
		writer << '};'
	}

	@Override
	public void integrate(Sensor node, Integrator integrator) {
		// TODO Auto-generated method stub

	}
}
