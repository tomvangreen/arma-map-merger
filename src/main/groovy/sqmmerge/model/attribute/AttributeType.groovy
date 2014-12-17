package sqmmerge.model.attribute

import sqmmerge.Integrator
import sqmmerge.model.MissionReader

public abstract class AttributeType<T>{

	public abstract T read(MissionReader reader)

	public abstract void write(Writer writer, T value)

	public abstract void integrate(Attribute master, Attribute other, Integrator integrator)

	public abstract T copy(T value)

	public abstract boolean check(MissionReader reader)
}
