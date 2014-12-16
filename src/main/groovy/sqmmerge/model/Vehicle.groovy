package sqmmerge.model

import sqmmerge.model.Writer.Control




public class Vehicle implements Node {
	public KeyValue position
	public KeyValue azimut
	public KeyValue offsetX
	public KeyValue offsetY
	public KeyValue special
	public KeyValue id
	public KeyValue side
	public KeyValue vehicle
	public KeyValue player
	public KeyValue leader
	public KeyValue rank
	public KeyValue skill
	public KeyValue syncId
	public KeyValue synchronizations
	public KeyValue init
	public KeyValue health
	public KeyValue ammo

	@Override
	public List<Node> getChildren() {
		[]
	}

	@Override
	public void read(Reader reader) {
		reader.info('Load Vehicle')
		def line = reader.getLine()
		while(!"};".equals(line)){
			if(line.startsWith("position[]=")){
				position = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("azimut=")){
				azimut = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("offsetX=")){
				offsetX = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("offsetY=")){
				offsetY = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("special=")){
				special = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("id=")){
				id = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("side=")){
				side = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("vehicle=")){
				vehicle = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("player=")){
				player = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("leader=")){
				leader = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("rank=")){
				rank = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("skill=")){
				skill = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("syncId=")){
				syncId = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("synchronizations[]=")){
				synchronizations = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("health=")){
				health = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("ammo=")){
				ammo = new KeyValue(line)
				reader.nextLine()
			}
			else if(line.startsWith("init=")){
				init = new KeyValue(line)
				reader.nextLine()
			}
			else{
				reader.err('Vehicle: Unknown situation: ', false)
				reader.err(line)
			}
			line = reader.getLine()
		}
	}

	@Override
	public void write(Writer writer) {
		writer << Control.Next << '{'
		writer << Control.Right

		position?.write(writer)
		azimut?.write(writer)
		offsetX?.write(writer)
		offsetY?.write(writer)
		special?.write(writer)
		id?.write(writer)
		side?.write(writer)
		vehicle?.write(writer)
		player?.write(writer)
		leader?.write(writer)
		rank?.write(writer)
		skill?.write(writer)
		ammo?.write(writer)
		init?.write(writer)
		health?.write(writer)
		syncId?.write(writer)
		synchronizations?.write(writer)
		writer << Control.Left << Control.Next
		writer << '};'
	}
}
