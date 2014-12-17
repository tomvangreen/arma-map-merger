package sqmmerge.model;

import sqmmerge.Integrator

public interface Node<T extends Node>{
	public List<Node> getChildren()

	public void read(MissionReader reader)

	public void write(Writer writer)

	public void integrate(T node, Integrator integrator)
}
