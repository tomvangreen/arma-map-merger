package sqmmerge.model

import sqmmerge.Integrator
import sqmmerge.model.Writer.Control



class MissionSet implements Node<MissionSet>{
	public String version
	public Mission mission
	public Mission intro
	public Mission outroWin
	public Mission outroLoose
	public final Writer writer = new Writer()

	@Override
	public List<Node> getChildren() {
		def list = new ArrayList<Node>()
		list.add(mission)
		list.add(intro)
		list.add(outroWin)
		list.add(outroLoose)
		return list;
	}

	@Override
	public void read(MissionReader reader) {
		reader.info("Loading Misison File")
		version = reader.getLine()
		reader.nextLine()

		//		try{
		reader.right();
		while(!reader.lastLine()){
			Mission mission = new Mission()
			mission.read(reader)

			if("Mission".equals(mission.name)){
				this.mission = mission
			}
			else if("Intro".equals(mission.name)){
				intro = mission
			}
			else if("OutroWin".equals(mission.name)){
				outroWin = mission
			}
			else if("OutroLoose".equals(mission.name)){
				outroLoose = mission
			}
			else{
				reader.err('Mission name not recognized: ' + mission.name)
			}
			reader.nextLine()
			while(!reader.eof() && "".equals(reader.getLine())){
				reader.nextLine()
			}
		}
		reader.left();
		//		}
		//		catch(Exception ex){
		//			reader.err(ex.getMessage(), false)
		//		}
	}

	@Override
	public void write(Writer writer) {
		writer << version
		mission?.write(writer)
		intro?.write(writer)
		outroWin?.write(writer)
		outroLoose?.write(writer)
		writer << Control.NewLine
	}

	public void load(String content){
		def reader = new ReaderImpl(content)
		version = null
		mission = null
		intro = null
		outroWin = null
		outroLoose = null
		read(reader)
	}

	@Override
	public String toString(){
		writer.clear()
		write(writer)
		writer.flush()
	}

	@Override
	public void integrate(MissionSet node, Integrator integrator) {
		if(!version.equals(node.version)){
			integrator.warn('Mission set version mismatch. ' + version + ' vs. ' + node.version)
		}
		mission = integrator.integrate(mission, node.mission)
		intro = integrator.integrate(intro, node.intro)
		outroWin = integrator.integrate(outroWin, node.outroWin)
		outroLoose = integrator.integrate(outroLoose, node.outroLoose)
	}

}
