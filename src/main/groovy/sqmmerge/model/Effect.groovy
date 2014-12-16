package sqmmerge.model

import sqmmerge.model.Writer.Control




public class Effect implements Node {



	@Override
	public List<Node> getChildren() {
		[]
	}

	@Override
	public void read(Reader reader) {
		def line = reader.getLine()
		while(!"};".equals(line)){
			//else{
			reader.err('Effect: Unknown situation: ', false)
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
}
