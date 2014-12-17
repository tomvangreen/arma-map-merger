package sqmmerge.model

import sqmmerge.Integrator
import sqmmerge.Main
import sqmmerge.model.Writer.Control



class Mission implements Node<Mission>{
	String name
	public StringArray addOns
	public StringArray addOnsAuto
	public KeyValue randomSeed
	public Intel intel
	public Groups groups
	public Vehicles vehicles	public Markers markers	public Sensors sensors
	@Override
	public List<Node> getChildren() {
		def list = new ArrayList<Node>()
		list.add(addOns)
		list.add(addOnsAuto)
		list.add(randomSeed)
		list.add(intel)
		list.add(groups)
		return list;
	}

	@Override
	public void read(MissionReader reader) {
		def line = reader.getLine()
		if(!line.startsWith('class ')){
			reader.err('Invalid content. Expceted class <class-name>', true)
			reader.err(line)
		}
		name = line.substring(6)
		if(Main.debug){
			reader.info('Loading Mission: ')
		}
		if(!reader.nextLine().equals("{")){
			reader.err("Mission: Expected '{'")
		}
		reader.nextLine()
		reader.right()
		while(!reader.getLine().equals("};")){
			readItem(reader)
		}
		reader.left()
	}

	void readItem(MissionReader reader){
		def line = reader.getLine()
		if("addOns[]=".equals(line)){
			addOns = new StringArray()
			addOns.read(reader)
			if(Main.debug){
				reader.info('Loaded addons')
			}
		}
		else if("addOnsAuto[]=".equals(line)){
			addOnsAuto = new StringArray()
			addOnsAuto.read(reader)
			if(Main.debug){
				reader.info('Loaded addonsAuto')
			}
		}
		else if(line.startsWith("randomSeed=")){
			randomSeed = new KeyValue(line)
			if(Main.debug){
				reader.info('Loaded random seed')
			}
			reader.nextLine()
		}
		else if("class Intel".equals(line)){
			intel = new Intel()
			intel.read(reader)
		}
		else if("class Vehicles".equals(line)){
			vehicles = new Vehicles()
			vehicles.read(reader)
		}
		else if("class Groups".equals(line)){
			groups = new Groups()
			groups.read(reader)
		}
		else if("class Markers".equals(line)){
			markers = new Markers()
			markers.read(reader)
		}
		else if("class Sensors".equals(line)){
			sensors = new Sensors()
			sensors.read(reader)
		}
		else{
			reader.err('Unhandled mission item: ', true)
			reader.err(line)
			reader.nextLine()
		}
	}


	@Override
	public void write(Writer writer) {
		writer << Control.Next << 'class ' << name
		writer << Control.Next << '{'
		writer << Control.Right

		addOns?.write(writer)
		addOnsAuto?.write(writer)
		randomSeed?.write(writer)
		intel?.write(writer)
		groups?.write(writer)
		vehicles?.write(writer)
		markers?.write(writer)
		sensors?.write(writer)

		writer << Control.Left << Control.Next
		writer << '};'
	}

	@Override
	public void integrate(Mission node, Integrator integrator) {
		if(!node){
			return
		}
		addOns = integrator.integrate(addOns, node.addOns)
		addOnsAuto = integrator.integrate(addOnsAuto, node.addOnsAuto)
		randomSeed = integrator.integrate(randomSeed, node.randomSeed)
		intel = integrator.integrate(intel, node.intel)
		groups = integrator.integrate(groups, node.groups)
		vehicles = integrator.integrate(vehicles, node.vehicles)
		markers = integrator.integrate(markers, node.markers)
		sensors = integrator.integrate(sensors, node.sensors)
	}
}
