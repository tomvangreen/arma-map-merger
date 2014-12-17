package sqmmerge.model.attribute;

import sqmmerge.Integrator
import sqmmerge.Writer
import sqmmerge.model.MissionReader

public class ModelObject {

	public List<Attribute> list = new ArrayList<Attribute>()
	public Map<String, Attribute> map = new HashMap<String, Attribute>()

	public void read(MissionReader reader) {
	}

	public void write(Writer writer) {
	}

	public void integrate(ModelObject other, Integrator integrator) {
	}

	public ModelObject copy() {
		null
	}
}
