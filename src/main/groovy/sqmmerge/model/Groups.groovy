package sqmmerge.model

import sqmmerge.model.Writer.Control



class Groups implements Node{

	public List<Group> groups = new ArrayList<Group>()

	@Override
	public List<Node> getChildren() {
		[]
	}

	@Override
	public void read(Reader reader) {
		def line = reader.nextLine()
		reader.info("Load Groups")
		if(!"{".equals(line)){
			reader.err("Groups: Expected '{'")
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
			reader.info('Loading Group ' + index)
			index++
			if(!line.startsWith("class Item")){
				reader.err('Groups: Expcted item block. Found: ', false)
				reader.err(line)
			}
			line = reader.nextLine()
			line = reader.nextLine()
			Group group = new Group()
			group.read(reader)
			groups.add(group)
			line = reader.nextLine()
		}
		reader.nextLine()
		reader.left()
	}

	@Override
	public void write(Writer writer) {
		writer << Control.Next << 'class Groups'
		writer << Control.Next << '{'
		writer << Control.Right

		if(groups.any()){
			writer << Control.Next << 'items=' << groups.size() << ';'
			for(int index = 0; index < groups.size(); index++){
				writer << Control.Next << 'class Item' << index
				groups.get(index).write(writer)
			}
		}

		writer << Control.Left << Control.Next
		writer << '};'
	}
}
