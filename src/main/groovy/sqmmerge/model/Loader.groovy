package sqmmerge.model

import java.util.regex.Pattern

import sqmmerge.util.Indent

class Loader {
	private String[] lines

	Pattern classPattern = ~/class (\w*)/
	Pattern listPattern = ~/items=([0-9]*);/
	Pattern simpleArrayPattern = ~/([\w]*)\[\]=/
	Pattern stringArrayItem = ~/^"([^"]*)",?$/

	private int row

	private final Indent indent = new Indent("  ")

	private def err(String message, boolean throwException = true){
		System.err.println("Line " + (row + 1) + ":\t"  + indent + message)
		if(throwException){
			throw new RuntimeException(message)
		}
	}
	private def info(String message){
		println("L" + (row + 1) + ":\t" + indent + message)
	}


	MissionSet load(String content){
		info("Loading Misison File")
		MissionSet set = new MissionSet()
		lines = content.split('\n')

		set.version = lines[0]


		try{
			row = 1
			indent.right();
			while(row < lines.length){
				loadMission(set)
				row ++
			}
			indent.left();
		}
		catch(Exception ex){
			err(ex.getMessage(), false)
		}
		set
	}

	def loadMission(MissionSet set){
		def line = getLine()
		if(debug){
			info('Loading Mission: ')
		}
		if(!line.startsWith('class ')){
			err('Invalid content. Expceted class <class-name>')
		}
		Mission mission = new Mission()
		mission.name = line.substring(6)
		if(debug){
		}
		if(!nextLine().equals("{")){
			err("Expected '{'")
		}
		row++
		indent.right()
		while(!getLine().equals("};")){
			loadMissionItem(mission)
		}
		indent.left()

		if(!nextLine().equals("};")){
			err("Expected '};'")
		}
		nextLine()

		if("Mission".equals(mission.name)){
			set.mission = mission
		}
		else if("Intro".equals(mission.name)){
			set.intro = mission
		}
		else if("OutroWin".equals(mission.name)){
			set.outroWin = mission
		}
		else if("OutroLoose".equals(mission.name)){
			set.outroLoose = mission
		}
		else{
			err('Mission name not recognized: ' + mission.name)
		}
	}
}
