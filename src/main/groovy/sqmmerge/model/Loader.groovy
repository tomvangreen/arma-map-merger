package sqmmerge.model

import java.util.regex.Pattern

import sqmmerge.MissionData





class Loader {
	private String[] lines
	public boolean debug = true;
	String indent = ""



	Pattern classPattern = ~/class (\w*)/
	Pattern listPattern = ~/items=([0-9]*);/
	Pattern simpleArrayPattern = ~/([\w]*)\[\]=/


	def indentRight(){
		indent = indent + " "

		//println(indent + "> Indent on: " + indent)
	}

	def indentLeft() {
		if(indent.length() > 1){
			indent = indent.substring(1)
		}
		else if(indent.length() == 1){
			indent = ""
		}
		//println(indent + "< Indent on: " + indent)
	}

	MissionData load(String content){
		MissionSet set = new MissionSet()
		lines = content.split('\n')

		set.version = lines[0]

		int row = 1
		while(row < lines.length){
			row = loadMission(set, row)
			row ++
		}

		set
	}

	int loadMission(MissionSet set, int row){
		def line = lines[row].trim()
		if(debug){
			println(indent + 'Loading Mission: ')
		}
		if(!line.startsWith('class ')){
			System.err.println(indent + 'Line ' + line + ': Invalid content. Expceted class <class-name>')
			return
		}
		Mission mission = new Mission()
		mission.name = line.substring(6)
		if("Mission".equals(mission.name)){
			set.mission = mission
		}
		else if("Intro"){
			set.intro = mission
		};
		else if("OutroWin"){
			set.outroWin = mission
		};
		else if("OutroLoose"){
			set.outroLoose = mission
		};
		else{
			System.err.println(indent + 'Line ' + line + ': Mission name not recognized: ' + mission.name)
		};
	}
}
