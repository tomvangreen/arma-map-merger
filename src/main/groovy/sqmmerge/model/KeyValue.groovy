package sqmmerge.model;

import sqmmerge.model.Writer.Control



public class KeyValue implements Node {

	///	public final Pattern pattern = ~/([^=]*)=([^;]*);/

	public String key
	public String value

	public KeyValue(){
	}

	public KeyValue(String line){
		def splitted = line.split('=')
		key = splitted[0]
		value = splitted[1].substring(0, splitted[1].size() - 1)
	}

	public KeyValue(String key, String value){
		this.key = key
		this.value = value
	}

	@Override
	public List<Node> getChildren() {
		return [];
	}

	@Override
	public void read(Reader reader) {
	}

	@Override
	public void write(Writer writer) {
		writer << Control.Next << key << '=' << value << ';'
	}

}
