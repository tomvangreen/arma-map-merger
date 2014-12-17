package sqmmerge.model

import sqmmerge.Integrator
import sqmmerge.model.Writer.Control




public class Effect implements Node<Effect> {



	@Override
	public List<Node> getChildren() {
		[]
	}

	@Override
	public void read(MissionReader reader) {
		def line = reader.getLine()
		while(!"};".equals(line)){
			//else{
			reader.err('Effect: Unknown situation: ', true)
			reader.err(line)
			//}
			line = reader.getLine()
		}
	}

	@Override
	public void write(Writer writer) {
		writer << Control.Next << '{'
		writer << Control.Right

		writer << Control.Left << Control.Next
		writer << '};'
	}

	@Override
	public void integrate(Effect node, Integrator integrator) {
	}
}
