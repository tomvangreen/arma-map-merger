package sqmmerge.model;

import sqmmerge.Integrator
import sqmmerge.model.Writer.Control


public abstract class ItemList<T extends Node<T>> implements Node<ItemList<T>>{

	public final List<T> items = new ArrayList<T>()

	public abstract String getListName()
	public abstract T instanciateChild()

	@Override
	public List<Node> getChildren() {
		items
	}

	@Override
	public void read(MissionReader reader) {
		def line = reader.nextLine()
		reader.info("Load " + getListName())
		if(!"{".equals(line)){
			reader.err("Vehicles: Expected '{'")
		}

		line = reader.nextLine()

		if("};".equals(line)){
			reader.nextLine()
			return
		}

		if(!line.startsWith("items=")){
			reader.err('Expected item count found: ', true)
			reader.err(line)
		}

		def count = line.split('=')[1].replace(';','') as int

		def index = 0

		line = reader.nextLine()

		reader.right()
		while(!"};".equals(line)){
			if(!line.startsWith("class Item")){
				reader.err('ItemList: Expected item block. Found: ', true)
				reader.err(line)
			}
			Node item = instanciateChild()
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
		writer << Control.Next << 'class ' << getListName()
		writer << Control.Next << '{'
		if(items.size() > 0){
			writer << Control.Right
			writer << Control.Next << 'items=' << items.size() << ';'
			for(int index = 0; index < items.size(); index++){
				writer << Control.Next << 'class Item' << index
				items.get(index).write(writer)
			}
			writer << Control.Left
		}
		writer << Control.Next << '};'
	}

	@Override
	public void integrate(ItemList<T> list, Integrator integrator){
		//TODO: Implement player filter logic probably here
		list.items.each{ items.add(it) }
	}
}
