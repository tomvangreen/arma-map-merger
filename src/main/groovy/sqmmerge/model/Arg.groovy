package sqmmerge.model

import sqmmerge.Integrator
import sqmmerge.model.Writer.Control





public class Arg implements Node<Arg> {

	public KeyValue value
	public KeyValue parentCls
	public KeyValue typeName

	@Override
	public List<Node> getChildren() {
		[]
	}

	@Override
	public void read(MissionReader reader) {
		def line = reader.getLine()
		while(!"};".equals(line)){
			if(line.startsWith("value=")){
				value = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("parentCls=")){
				parentCls = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("typeName=")){
				typeName = new KeyValue(line)
				reader.nextLine()
			}
			else{
				reader.err('Arg: Unknown situation: ', true)
				reader.err(line)
			}
			line = reader.getLine()
		}
	}

	@Override
	public void write(Writer writer) {
		writer << Control.Next << '{'
		writer << Control.Right

		value?.write(writer)
		parentCls?.write(writer)
		typeName?.write(writer)

		writer << Control.Left << Control.Next
		writer << '};'
	}

	@Override
	public void integrate(Arg node, Integrator integrator) {
	}
}
