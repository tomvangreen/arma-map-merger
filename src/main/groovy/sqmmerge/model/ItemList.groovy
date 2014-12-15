package sqmmerge.model;


public abstract class ItemList<T extends Node> implements Node{

	public String listName
	public final List<T> items = new ArrayList<T>()

	public abstract T instanciate()

	@Override
	public List<Node> getChildren() {
		items
	}

	@Override
	public void read(Reader reader) {
		def line = reader.nextLine()
		reader.info("Load Groups")
		if(!"{".equals(line)){
			reader.err("Vehicles: Expected '{'")
		}

		line = reader.nextLine()

		if(!line.startsWith("items=")){
			reader.err('Expected item count found: ', false)
			reader.err(line)
		}

		def count = line.split('=')[1].replace(';','') as int

		def index = 0

		line = reader.nextLine()

		reader.right()
		while(!"};".equals(line)){
			if(!line.startsWith("class Item")){
				reader.err('ItemList: Expected item block. Found: ', false)
				reader.err(line)
			}
			Node item = instanciate()
			line = reader.nextLine()
			line = reader.nextLine()
			item.read(reader)
			items.add(item)
			line = reader.nextLine()
		}
		reader.nextLine()
		reader.left()
	}

	@Override
	public void write(Writer writer) {
	}
}
