package sqmmerge.model.attribute

import sqmmerge.Integrator

class Attribute<T> {
	public final String name
	public final AttributeType type
	public T value


	public Attribute(String name, AttributeType type){
		this(name, type, null)
	}

	public Attribute(String name, AttributeType type, T value){
		this.name = name
		this.type = type
		this.value = value
	}

	public void read(Reader reader){
		value = type.read(reader)
	}

	public void write(Writer writer){
		type.write(writer, value)
	}

	public void integrate(Attribute other, Integrator integrator){
		type.integrate(this, other, integrator)
	}

	public Attribute copy(){
		new Attribute<T>(name, type, type.copy(value))
	}
}
