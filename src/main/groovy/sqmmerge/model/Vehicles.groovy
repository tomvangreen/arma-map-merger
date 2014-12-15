package sqmmerge.model

import sqmmerge.model.Writer.Control

//TODO: Vehicles and Group should both have a base class handling stuff ;)

class Vehicles implements Node{

	public List<Vehicle> vehicles = new ArrayList<Vehicle>()

	@Override
	public List<Node> getChildren() {
		[]
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
			reader.info('Loading Vehicles ' + index)
			index++
			if(!line.startsWith("class Item")){
				reader.err('Vehicles: Expected item block. Found: ', false)
				reader.err(line)
			}
			Vehicle vehicle = new Vehicle()
			line = reader.nextLine()
			line = reader.nextLine()
			vehicle.read(reader)
			vehicles.add(vehicle)
			line = reader.nextLine()
		}
		reader.nextLine()
		reader.left()
	}

	@Override
	public void write(Writer writer) {
		writer << Control.Next << 'class Vehicles'
		writer << Control.Next << '{'
		writer << Control.Right

		if(vehicles.any()){
			writer << Control.Next << 'items=' << vehicles.size() << ';'
			for(int index = 0; index < vehicles.size(); index++){
				writer << Control.Next << 'class Item' << index
				vehicles.get(index).write(writer)
			}
		}

		writer << Control.Left << Control.Next
		writer << '};'
	}
}
