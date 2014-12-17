package sqmmerge.model.attribute;

import java.util.regex.Pattern

import sqmmerge.Integrator
import sqmmerge.model.MissionReader


public class KeyValueType extends AttributeType<String> {

	public final static Pattern pattern = ~/^([^(=|\[\])]*)=(.*);$/

	@Override
	public String read(MissionReader reader) {
		null
	}

	@Override
	public void write(Writer writer, String value) {
	}

	@Override
	public void integrate(Attribute master, Attribute other, Integrator integrator) {
	}

	@Override
	public String copy(String value) {
		return null
	}

	@Override
	public boolean check(MissionReader reader) {
		pattern.matcher(reader.getLine())
	}
}
