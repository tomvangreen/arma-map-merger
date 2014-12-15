package sqmmerge.model;

public interface Node{
	public List<Node> getChildren()

	public void read(Reader reader)

	public void write(Writer writer)
}
